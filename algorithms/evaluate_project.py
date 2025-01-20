import sys
import os
import subprocess
import io

latex_output = {
    "depth" : "",
    "polymorphism": "",
    "multipath" : "",
    "payload-construction" : "",
    "serialization-variants" : "",
    "keywords" : "",
    "reflection-methodinvoke" : "",
    "reflection-clinit" : "",
    "reflection-classloading" : "",
    "reflection-constructor" : "",
    "reflection-proxy" : "",
    "reflection-exceptions" : "",
    "reflection-metaobjects" : "",
    "lambda" : "",
    "jni" : "",
}

ysoserial_total = 11
ysoserial_fp_total = 1
ysoserial_found = 0
ysoserial_fp_found = 0

project = sys.argv[1]
skip_run = sys.argv[2]
only_invoke = sys.argv[3]

if skip_run == "y":

    for file in os.listdir("_target"):
        if file == "gleipner.chains-1.0.jar":
            continue
        if not "ysoserial" in file:
            continue
            
        if sys.argv[3] == "y" and not ("cc1x" in file or "romex" in file or "spring" in file or "vaadin" in file):
            continue

        subprocess.run(["python3", "algorithms.py", "--store", "--run", "-p", project, "--jar", os.path.join("_target", file)])

print("[INFO]: Switching to evaluation")

for file in os.listdir("_target"):
    if file == "gleipner.chains-1.0.jar":
        continue

    test_case = file.replace('gleipner.chains-1.0-', '').replace(".jar", "")
    process = subprocess.Popen(["java", "-jar", "evaluator.jar", os.path.join("_output",
            f"{project.replace('serhybrid', 'serhybridpub').replace('jchainz', 'JChainz')}_{test_case}.txt") , os.path.join("_target", file), project],
                     stdout=subprocess.PIPE)

    the_key = ""
    is_ysoserial = False
    for line in io.TextIOWrapper(process.stdout):
        if "File:" in line:
            for key in latex_output:
                if key in line:
                    the_key = key
                    break

            if "ysoserial" in line:
                is_ysoserial = True
            else:
                is_ysoserial = False

        if the_key == "":
            continue

        if "positives" in line:
            result_string = line.split(" ")[2].replace("\x1b[32m", "").replace("\x1b[0m", "").replace("\x1b[31m", "").replace("\n", "")
            found = int(result_string.strip().split("/")[0])
            total = int(result_string.strip().split("/")[1])

            if "True" in line:

                if is_ysoserial:
                    ysoserial_found += 1
                else:
                    if total == found:
                        result_string = "\cellcolor{green!25}" + result_string
                    else:
                        result_string = "\cellcolor{red!25}" + result_string
                    latex_output[the_key] += result_string

            elif "False" in line:

                if is_ysoserial:
                    ysoserial_fp_found += 1
                else:
                    if found == 0:
                        result_string = "\cellcolor{green!25}" + result_string
                    else:
                        result_string = "\cellcolor{red!25}" + result_string
                    latex_output[the_key] += " & " + result_string


if ysoserial_total == ysoserial_found:
    ysoserial_tp_str = "\cellcolor{green!25}" + f"{ysoserial_found}/{ysoserial_total}"
else:
    ysoserial_tp_str = "\cellcolor{red!25}" + f"{ysoserial_found}/{ysoserial_total}"

if ysoserial_fp_found == 0:
    ysoserial_fp_str = "\cellcolor{green!25}" + f"{ysoserial_fp_found}/{ysoserial_fp_total}"
else:
    ysoserial_fp_str = "\cellcolor{red!25}" + f"{ysoserial_fp_found}/{ysoserial_fp_total}"

latex_output["ysoserial"] = f"{ysoserial_tp_str} & {ysoserial_fp_str} & "

with open("perf.log", "r") as performance_file:
    the_key = ""
    for line in performance_file.readlines():
        if "Running on" in line:
            for key in latex_output:
                if key in line:
                    the_key = key
                    break
        if "::all" in line:
            exec_time = float(line.strip().split(" ")[-2])
            if the_key != "":
                latex_output[the_key] += " & " + str(round(exec_time, 1)) + "s"
                the_key = ""


for key in latex_output:
    print(f"{key} : {latex_output[key]}")

