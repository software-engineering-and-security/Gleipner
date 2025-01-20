import os

# Constants referring to placeholder strings in template files
CLASS_NAME = "${class}"
LINKGADGET_CLASS_NAME = "${linkGadgetClass}"
LINKGADGET_VAR_NAME = "${linkGadgetVar}"
PARENT_CLASS_NAME = "${parentClass}"
ANNOTATION = "${annotation}"
PACKAGE = "${package}"
LINKGADGET_ARRAY = "${LINKGADGET_ARRAY}"


# ---------------------------------




class Template:
    def __init__(self, name: str, package: str, class_name: str = "", linkgadget_class_name: str = "",
                 linkgadget_var_name: str = "linkGadget", annotations: list = None, parent_class_name: str = ""):

        if annotations is None:
            annotations = []

        try:
            self.content = open(os.path.join("templates", f"{name}.template")).read()
        except FileNotFoundError:
            print(f"[ERROR] Could not find template file {name}.template")

        self.package = package
        self.class_name = class_name
        self.linkgadget_class_name = linkgadget_class_name
        self.linkgadget_var_name = linkgadget_var_name
        self.annotations = annotations
        self.parent_class_name = parent_class_name

    def get_concretized_template(self):

        annotation_string = ""
        for i in range(len(self.annotations)):
            annotation_string += self.annotations[i]
            if i < len(self.annotations) - 1:
                annotation_string += "\n"

        output = (self.content
                  .replace(PACKAGE, self.package)
                  .replace(CLASS_NAME, self.class_name)
                  .replace(LINKGADGET_CLASS_NAME, self.linkgadget_class_name)
                  .replace(LINKGADGET_VAR_NAME, self.linkgadget_var_name)
                  .replace(ANNOTATION, annotation_string)
                  .replace(PARENT_CLASS_NAME, self.parent_class_name))

        return output

    def add_depth_annotation(self, depth: int):
        self.annotations.append(f"@ChainLength({depth})")
    def add_access_paths_annotation(self, paths: int):
        self.annotations.append(f"@SinkAccessPaths({paths})")
        
class MultipathTrampolineTemplate(Template):

    def __init__(self, package, prefix, count):
        self.count = count
        self.prefix = prefix
        Template.__init__(self, "TrampolineGadget", package, class_name=f"Multipath_{self.prefix}_TrampolineGadget")

    def get_concretized_template(self):
        output = f'''package {self.package};
        import gleipner.core.annotations.InterProcedural;
        import gleipner.core.GleipnerObject;
        '''

        output += "public class Multipath_" + self.prefix + "_TrampolineGadget extends GleipnerObject {\n"

        for i in range(1, self.count + 1):
            output += f"public Multipath_{self.prefix}_LinkGadget{i} linkGadget{i};\n"

        output += '''public int choice;
        @Override
        @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
        public int hashCode() {
            switch (choice) {    
        '''

        for i in range(1, self.count + 1):
            output += f"case {i}:\n"
            output += f"linkGadget{i}.linkMethod();\n"

        output += '''default:
            break;
        }
        return super.hashCode();
        }
    }    
        '''
        return output

