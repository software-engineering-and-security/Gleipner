import subprocess
import shutil
import os
import sys

for i in range(1,16):
    padded_num = f'{i:03}'
    subprocess.run(["jar", "xf", "gleipner.chains-1.0-payload-construction.jar"], cwd="_target")

    gadget_path = os.path.join("_target", "gleipner", "chains", "taint")

    for file in os.listdir(gadget_path):
        file_path = os.path.join(gadget_path, file)
        if not padded_num in file and "Taint" in file:
            os.remove(file_path)

    subprocess.run(["jar", "cf", f"gleipner.chains-1.0-payload-construction-{padded_num}.jar", "gleipner", "META-INF"], cwd="_target")
    shutil.rmtree(os.path.join("_target", "gleipner"))
    shutil.rmtree(os.path.join("_target", "META-INF"))
    subprocess.run(["python3", "algorithms.py", "--jar", f"_target/gleipner.chains-1.0-payload-construction-{padded_num}.jar", "--run", "-p", sys.argv[1]])
    subprocess.run(["python3", "algorithms.py", "--jar", f"_target/gleipner.chains-1.0-payload-construction-{padded_num}.jar", "--store", "-p", sys.argv[1]])





