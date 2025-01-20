import datetime
import os
import subprocess
import shutil
import argparse
import re
import timeit

from neo4j import GraphDatabase

##### CONFIG #####
GITHUB_PROJECTS = {
    "serianalyzer": "https://github.com/mbechler/serianalyzer.git",
    "gadgetinspector": "https://github.com/JackOfMostTrades/gadgetinspector.git",
    "serhybridpub": "https://bitbucket.org/unshorn/serhybridpub.git",
    "JChainz": "https://github.com/Kigorky/JChainz.git",
    "Crystallizer": "https://github.com/HexHive/Crystallizer.git",
    "tabby": "https://github.com/wh1t3p1g/tabby.git"
}

# An older JDK is required to execute gradle script in gadgetinspector
JDK_8 = "/home/jvm/oracle/8/jdk1.8.0_221"
JDK_11 = "/home/jvm/openjdk/11/jdk-11.0.2"
GRADLE_7 = "/opt/gradle/7.1.1/bin/gradle"
CONFIG_DIR = "_config"
OUTPUT_DIR = "_output"
TIME_FILE = "perf.log"

RESULTS_DIR = ""
TARGET_DIR = ""
CONTAINER_NAME = ""
#################

def replace_file(project: str, filename: str, filepath: str):
    replace_path = os.path.join(project, filepath, filename)
    os.remove(replace_path)
    shutil.copyfile(os.path.join(CONFIG_DIR, project, filename), replace_path)


def log_time(time, project_name, execution_phase="all"):
    with open(TIME_FILE, "a") as performance_log:
        performance_log.write(f"\t[{project_name}::{execution_phase}] execution completed in {time} seconds\n")


def new_perf_log(jar_file):
    with open(TIME_FILE, "a") as performance_log:
        performance_log.write(f"\nRunning on {jar_file} at {datetime.datetime.now()}\n")


class Project:

    def __init__(self, clone_url, directory, env, debug):
        self.clone_url = clone_url
        self.directory = directory
        self.analyze_jars = []
        self.env = env
        self.debug = debug

    def clone(self):
        if os.path.exists(self.directory):
            print(f"[INFO] Project {self.directory} appears to already have been cloned ... skipping")
            return
        print(f"[INFO] Cloning project {self.directory} ...")
        proc = subprocess.run(["git", "clone", self.clone_url], stdout=subprocess.PIPE, text=True)
        if self.debug:
            print(proc.stdout)

    def compile_jar(self):
        proc = subprocess.run(['mvn', 'clean', 'package'], cwd='..', env=self.env, stdout=subprocess.PIPE, text=True)

        if self.debug:
            print(proc.stdout)

        if "BUILD SUCCESS" in proc.stdout:
            print("[INFO] Gleipnir built successfully")
        else:
            print("[ERROR] Build of Gleipnir failed")

        shutil.copy(os.path.join('..', 'chains', 'target', 'gleipner.chains-1.0-SNAPSHOT.jar'), 'gleipner.jar')

    def setup(self):
        if not os.path.exists(self.directory):
            print(f"Could not find project {self.directory}. Run clone first?")
            return

    def run(self):
        if not os.path.exists(self.directory):
            print(f"Could not find project {self.directory}. Run clone first?")
            return

    def get_result(self):
        print(f"[ERROR] result view not implemented for {self.directory}")


class Crystallizer(Project):
    def __init__(self, debug=False):
        name = "Crystallizer"
        env = os.environ.copy()
        env["JAVA_HOME"] = JDK_11
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)

    def setup(self):
        Project.setup(self)
        subprocess.run(["docker", "pull", "prashast94/crystallizer:latest"])
        if not os.path.exists(RESULTS_DIR):
            os.mkdir(RESULTS_DIR)
        if not os.path.exists(TARGET_DIR):
            os.mkdir(TARGET_DIR)
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "SinkAnalysis.java"),
                       os.path.join(TARGET_DIR, "SinkAnalysis.java"))
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "LibSpecificRules.java"),
                        os.path.join(TARGET_DIR, "LibSpecificRules.java"))
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "run_campaigns.sh"),
                        os.path.join(TARGET_DIR, "run_campaigns.sh"))
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "potential_sinks"),
                        os.path.join(TARGET_DIR, "potential_sinks"))

        subprocess.run(["docker", "run", "--security-opt", "seccomp=unconfined", "--name", CONTAINER_NAME, "-v",
                        f"{os.path.join(os.getcwd(), TARGET_DIR)}:/root/SeriFuzz/targets", "-v",
                        f"{os.path.join(os.getcwd(), RESULTS_DIR)}:/root/SeriFuzz/results", "--rm",
                         "-t", "-d", "prashast94/crystallizer:latest"])

    def run(self):
        if not os.path.exists(os.path.join(TARGET_DIR, "gleipner")):
            os.mkdir(os.path.join(TARGET_DIR, "gleipner"))

        base_file_name = os.path.split(self.analyze_jars[0])[-1]
        shutil.copyfile(self.analyze_jars[0], os.path.join(TARGET_DIR, "gleipner", "gleipner.jar"))
        print("Copy command into console:")
        dynamic_sink_id_time = "60"
        concretization_time = EXECUTION_TIME
        print(
            f"eval/run_campaigns.sh /root/SeriFuzz/targets/gleipner/gleipner.jar {dynamic_sink_id_time} {concretization_time}")

        # copy lib specific rules with uncommented hashCode into container
        subprocess.run(
            ["docker", "exec", "crystallize_test", "rm", "-r", "/root/SeriFuzz/results/concretization/crashes"])
        t_start_total = timeit.default_timer()
        # copy lib specific rules with uncommented hashCode into container
        subprocess.run(["docker", "exec", CONTAINER_NAME, "cp", "/root/SeriFuzz/targets/LibSpecificRules.java",
                        "/root/SeriFuzz/src/static/src/main/java/analysis/LibSpecificRules.java"])
        subprocess.run(["docker", "exec", CONTAINER_NAME, "cp", "/root/SeriFuzz/targets/SinkAnalysis.java",
                        "/root/SeriFuzz/src/static/src/main/java/analysis/SinkAnalysis.java"])
        # subprocess.run(["docker", "exec", CONTAINER_NAME, "cp", "/root/SeriFuzz/targets/potential_sinks", "/root/SeriFuzz/jazzer/crashes/potential_sinks"])
        subprocess.run(["docker", "exec", CONTAINER_NAME, "cp", "/root/SeriFuzz/targets/run_campaigns.sh",
                       "/root/SeriFuzz/eval/run_campaigns.sh"])
        subprocess.run(["docker", "exec", CONTAINER_NAME, "./eval/run_campaigns.sh",
                        "/root/SeriFuzz/targets/gleipner/gleipner.jar", dynamic_sink_id_time, concretization_time])
        log_time(timeit.default_timer() - t_start_total, self.directory)

        with open(os.path.join(RESULTS_DIR, "concretization", "candidate_paths"), "r") as paths_file:
            do_skip = True
            for line in paths_file.readlines():
                if "===" in line:
                    do_skip = False
                if do_skip or line.strip() == "":
                    continue
                print(line)

        subprocess.run(
            ["docker", "exec", CONTAINER_NAME, "python", "./eval/concretized_paths.py", "--concretized_ids",
             "/root/SeriFuzz/results/concretization/crashes", "--paths",
             "/root/SeriFuzz/results/concretization/candidate_paths"])
        subprocess.run(["docker", "exec", CONTAINER_NAME, "cp", "./eval/concretized_paths.json",
                        "/root/SeriFuzz/results/concretized_paths.json"])

        def get_result(self):
            with open(os.path.join(self.directory, "results", "concretized_paths.json"), "r") as results_file:
                return results_file.read()


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--setup", action='store_true')
    parser.add_argument("--run", action='store_true')
    parser.add_argument("-p", "--project", help="select specific project")
    parser.add_argument("--debug", action='store_true')
    parser.add_argument("--jar", nargs=1, help="the gleipner jarfile to analyze")
    parser.add_argument("--show", action='store_true',
                        help="output the results, having this extra option avoids having to run multiple times to only view results (just like hashcat :3)")
    parser.add_argument("--store", action="store_true", help="save output to file")
    parser.add_argument("--log", help="save log to file with name [arg]")
    parser.add_argument("--result_dir")
    parser.add_argument("--target_dir")
    parser.add_argument("--container_name")
    parser.add_argument("--execution_time")

    args = parser.parse_args()

    global CONTAINER_NAME
    global RESULTS_DIR
    global TARGET_DIR
    global EXECUTION_TIME

    EXECUTION_TIME = args.execution_time
    CONTAINER_NAME = args.container_name
    RESULTS_DIR = os.path.join(CONTAINER_NAME, "results")
    TARGET_DIR = os.path.join(CONTAINER_NAME, "targets")

    if not os.path.exists(CONTAINER_NAME):
        os.mkdir(CONTAINER_NAME)

    if args.project:
        if args.project == "serianalyzer":
            projects = [Serianalyzer(args.debug)]
        elif args.project == "gadgetinspector":
            projects = [GadgetInspector(args.debug)]
        elif args.project == "serhybrid":
            projects = [SerHybrid(args.debug)]
        elif args.project == "jchainz":
            projects = [JChainz(args.debug)]
        elif args.project == "crystallizer":
            projects = [Crystallizer(args.debug)]
        elif args.project == "tabby":
            projects = [Tabby(args.debug)]
    else:
        projects = [Serianalyzer(args.debug), GadgetInspector(args.debug), SerHybrid(args.debug), JChainz(args.debug),
                    Crystallizer(args.debug), Tabby(args.debug)]

    if args.log:
        TIME_FILE = args.log

    if args.setup:
        for p in projects:
            p.clone()
            p.setup()

    jar_file = "gleipner.jar"
    if args.jar:
        jar_file = args.jar[0]

    if args.run:
        new_perf_log(jar_file)
        for p in projects:
            p.analyze_jars.append(jar_file)
            p.run()

    if args.show:
        for p in projects:
            if len(p.analyze_jars) == 0:
                p.analyze_jars.append(jar_file)
            print(p.get_result())

    if args.store:
        if not os.path.exists(OUTPUT_DIR):
            os.mkdir(OUTPUT_DIR)

        for p in projects:
            if len(p.analyze_jars) == 0:
                p.analyze_jars.append(jar_file)

            file_name = f"{p.directory}_{jar_file.replace('_target/gleipner.chains-1.0-', '').replace('.jar', '')}.txt"
            with open(os.path.join(OUTPUT_DIR, file_name), "w") as out_file:
                out_file.write(p.get_result())

    return 0


if __name__ == "__main__":
    main()
