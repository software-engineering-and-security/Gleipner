import os
import shutil
import glob
import re

# Make a copy of the original gleipner project specifically for evaluating JChainz.
# The reason for this is that JChainz Data dependency graph filters out the GleipnerObject.hashCode setup of Gleipner (which is bad in itself)
# To at least evaluate the chain finding capacity of JChainz we replace all hashCode methods in GleipnerObjects with readObject and recompile

CHAINS_DIRS = ["chains", "compiler", "evaluator"]

shutil.rmtree("tmp", ignore_errors=True)

for dir in CHAINS_DIRS:
    shutil.copytree(os.path.join("..", dir), os.path.join("tmp", dir))
shutil.copy(os.path.join("..", "pom.xml"), os.path.join("tmp", "pom.xml"))
shutil.rmtree(os.path.join("tmp", "chains", "src", "test"))

for file in glob.iglob(os.path.join("tmp", "chains", "src", "main", "java") + "/**/*.java", recursive=True):

    is_gleipner_object = False
    is_hash_method = False
    is_ysoserial = False
    lines = []
    brackets = 0
    line_cnt = 0
    last_override = -1

    if "Transient" in file:
        continue

    if "ysoserial" in file:
        is_ysoserial = True
        continue

    with open(file, "r") as source_file:
        for line in source_file.readlines():
            line_cnt+=1

            if "@Override" in line:
                last_override = line_cnt

            if "extends GleipnerObject" in line:
                is_gleipner_object = True
                lines.insert(1, "import java.io.*;")

                if "implements" in line:
                    line = line.replace("extends GleipnerObject", "")
                    if not "Serializable" in line:
                        line = line.replace("implements", "implements Serializable,")
                        lines.insert(1, "import java.io.Serializable;")
                        line_cnt += 1

                else:
                    line = line.replace("extends GleipnerObject", "implements Serializable")
                    lines.insert(1, "import java.io.Serializable;")
                    line_cnt += 1

                lines.append(line.replace("\n", ""))
                continue

            if "public int hashCode()" in line and is_gleipner_object:
                is_hash_method = True
                lines.pop(last_override)
                lines.append("private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {")
                lines.append("ois.defaultReadObject();")
                continue

            elif "return" in line and is_hash_method:
                line = line.replace("return 0;", "return;")
                line = line.replace("return super.hashCode();", "return;")
                line = line.replace("return hashCode;", "return;")
                lines.append(line.replace("\n", ""))
                continue

            if "{" in line and is_hash_method:
                brackets += 1

            if "}" in line and is_hash_method:
                if brackets == 0:
                    is_hash_method = False
                else:
                    brackets -= 1

            lines.append(line.replace("\n", ""))

    if is_gleipner_object:
        with open(file, "w") as source_file:
            for line in lines:
                source_file.write(line + "\n")
