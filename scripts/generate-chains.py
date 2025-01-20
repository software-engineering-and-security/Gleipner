import os
import argparse
import shutil
from templates.templates import Template, MultipathTrampolineTemplate

CHAIN_SOURCE_DIR = os.path.join("..", "chains", "src", "main", "java", "gleipner", "chains")
POM_FILE = os.path.join("..", "chains", "pom.xml")


def write_to_source(subdir, gadget_chain):
    depth_directory = os.path.join(CHAIN_SOURCE_DIR, subdir)
    for gadget in gadget_chain:
        (open(os.path.join(depth_directory, f"{gadget.class_name}.java"), "w")
         .write(gadget.get_concretized_template()))


def generate_trampoline_gadget(prefix: str, package, test_series_name, link_gadget):
    trigger_template = Template("TrampolineGadget", package=package,
                                class_name=f"{test_series_name}_{prefix}_TrampolineGadget",
                                linkgadget_class_name=f"{test_series_name}_{prefix}_{link_gadget}")

    return trigger_template


def generate_sink_gadget(prefix: str, depth: int, package, test_series_name, sink_access_paths=1):
    trigger_template = Template("SinkGadget", package=package, class_name=f"{test_series_name}_{prefix}_SinkGadget")
    trigger_template.add_depth_annotation(depth)
    trigger_template.add_access_paths_annotation(sink_access_paths)

    return trigger_template


def generate_multipath_chain(prefix: str, paths: int, do_write=False):
    gadget_chain = []
    gadget_chain.append(MultipathTrampolineTemplate("gleipner.chains.multipath", prefix, paths))
    for i in range(1, paths + 1):
        gadget_chain.append(generate_multipath_link_gadget(prefix, i))
    gadget_chain.append(generate_sink_gadget(prefix, 4, "gleipner.chains.multipath", "Multipath", sink_access_paths=paths))

    if do_write:
        write_to_source("multipath", gadget_chain)

    return gadget_chain


def generate_multipath_link_gadget(prefix: str, index: int, package="gleipner.chains.multipath"):
    link_template = Template("LinkGadget", package=package, class_name=f"Multipath_{prefix}_LinkGadget{index}",
                             linkgadget_class_name=f"Multipath_{prefix}_SinkGadget")

    return link_template


def generate_depth_chain(prefix, depth=5, do_write=False):
    gadget_chain = []

    gadget_chain.append(generate_trampoline_gadget(prefix, "gleipner.chains.depth", "Depth", "LinkGadget1"))
    for i in range(1, depth - 1):
        gadget_chain.append(generate_depth_link_gadget(prefix, i, depth))
    gadget_chain.append(generate_sink_gadget(prefix, depth + 1, "gleipner.chains.depth", "Depth"))

    if do_write:
        write_to_source("depth", gadget_chain)

    return gadget_chain


def generate_depth_link_gadget(prefix: str, index: int, depth: int):
    trigger_template = Template("LinkGadget", package="gleipner.chains.depth",
                                class_name=f"Depth_{prefix}_LinkGadget{index}")

    if index >= depth - 2:
        trigger_template.linkgadget_class_name = f"Depth_{prefix}_SinkGadget"
        trigger_template.linkgadget_var_name = "sinkGadget"
    else:
        trigger_template.linkgadget_class_name = f"Depth_{prefix}_LinkGadget{index + 1}"

    return trigger_template


def generate_polymorphism_chain(prefix, children_per_layer, inheritance_depth, do_write=False):
    gadget_chain = []
    gadget_chain.append(generate_trampoline_gadget(prefix, "gleipner.chains.polymorphism", "Polymorphism", "Parent"))
    gadget_chain.append(generate_polymorphism_parent(prefix))

    count = generate_polymorphism_tree_recurse(prefix, children_per_layer, inheritance_depth, 0, gadget_chain, "Parent",
                                               1) - 1
    last_child = gadget_chain.pop(-1)
    gadget_chain.append(generate_polymorphism_link_gadget(prefix, last_child.parent_class_name))

    gadget_chain.append(generate_sink_gadget(prefix, 3, "gleipner.chains.polymorphism", "Polymorphism"))

    if do_write:
        write_to_source("polymorphism", gadget_chain)

    return gadget_chain


def generate_polymorphism_tree_recurse(prefix, children_per_layer, inheritance_depth, current_depth, gadget_chain,
                                       parent, index):
    if current_depth == inheritance_depth:
        return index

    for i in range(children_per_layer):
        gadget_chain.append(generate_polymorphism_child(prefix, parent, index))
        index = generate_polymorphism_tree_recurse(prefix, children_per_layer, inheritance_depth, current_depth + 1,
                                                   gadget_chain, f"Child{index}", index + 1)

    return index


def generate_polymorphism_child(prefix, parent, index):
    child_template = Template("Child", package="gleipner.chains.polymorphism",
                              class_name=f"Polymorphism_{prefix}_Child{index}",
                              parent_class_name=f"Polymorphism_{prefix}_{parent}")
    return child_template


def generate_polymorphism_parent(prefix):
    return Template("Parent", package="gleipner.chains.polymorphism", class_name=f"Polymorphism_{prefix}_Parent")


def generate_polymorphism_link_gadget(prefix, parent_class):
    link_template = Template("LinkGadget_Child", package="gleipner.chains.polymorphism",
                             class_name=f"Polymorphism_{prefix}_LinkGadget",
                             linkgadget_class_name=f"Polymorphism_{prefix}_SinkGadget",
                             linkgadget_var_name="sinkGadget",
                             parent_class_name=parent_class)

    return link_template


def get_next_prefix(dir):
    files = os.listdir(os.path.join(CHAIN_SOURCE_DIR, dir))
    prefix = "001"
    if len(files) > 0:
        files.sort(reverse=True)
        prefix = files[0].replace(f"{dir.title()}_", "")[0:3]
        while prefix[0] == '0':
            prefix = prefix[1:]
        prefix = f"{int(prefix) + 1:03}"
    return prefix


if __name__ == '__main__':

    for directory in ["depth", "polymorphism", "multipath"]:
        if not os.path.exists(os.path.join(CHAIN_SOURCE_DIR, directory)):
            os.mkdir(os.path.join(CHAIN_SOURCE_DIR, directory))

    parser = argparse.ArgumentParser()
    parser.add_argument("--depth", type=int, nargs=2,
                        help="lower boundary and count, e.g.: 1 4 --> produces 4 gadget chains beginning with depth 5, 6, 7, 8. Note that 0 refers to the minimum depth of 4, since there at least needs to be the TriggerGadgets (readObject+hashCode),LinkGadget and SinkGadget")
    parser.add_argument("--polymorphism", type=int, nargs=2,
                        help="generate an inheritance hierarchy with args[0] children per layer and a depth of args[1] ")
    parser.add_argument("--multipath", type=int, nargs=1, help="create a chain with args[0] alternative access paths")
    parser.add_argument("--clean", action="store_true",
                        help="Remove all gadgets in the depth, polymorphism and multipath directories")
    parser.add_argument("--preview", action="store_true",
                        help="Print the templates to screen instead of writing them to the Java module")
    args = parser.parse_args()

    chain = []

    if args.polymorphism:

        # get highest prefix
        prefix = get_next_prefix("polymorphism")
        chain = generate_polymorphism_chain(prefix, children_per_layer=args.polymorphism[0],
                                            inheritance_depth=args.polymorphism[1], do_write=not args.preview)

    elif args.depth:
        shutil.rmtree(os.path.join(CHAIN_SOURCE_DIR, "depth"))
        os.mkdir(os.path.join(CHAIN_SOURCE_DIR, "depth"))
        for i in range(args.depth[0], args.depth[1] + 1):
            chain = generate_depth_chain(f"{i+1:03}", i + 3, do_write=not args.preview)

    elif args.multipath:
        prefix = get_next_prefix("multipath")
        chain = generate_multipath_chain(prefix, args.multipath[0], do_write=not args.preview)

    if args.clean:
        for directory in ["depth", "polymorphism", "multipath"]:
            if os.path.exists(os.path.join(CHAIN_SOURCE_DIR, directory)):
                shutil.rmtree(os.path.join(CHAIN_SOURCE_DIR, directory))

    if args.preview:
        for gadget in chain:
            print(gadget.get_concretized_template())
