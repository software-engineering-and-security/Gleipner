import os
import subprocess
import shutil
import argparse
import glob
import re

##### CONFIG #####
GITHUB_PROJECTS = {
    "serianalyzer": "https://github.com/mbechler/serianalyzer.git",
    "gadgetinspector": "https://github.com/JackOfMostTrades/gadgetinspector.git"
}

# An older JDK is required to execute gradle script in gadgetinspector
JDK_8 = "/home/jvm/oracle/8/jdk1.8.0_221"
JDK_11 = "/home/jvm/openjdk/11/jdk-11.0.2"
GRADLE_7 = "/opt/gradle/7.1.1/bin/gradle"
CONFIG_DIR = "_config"


#################

def replace_file(project: str, filename: str, filepath: str):
    replace_path = os.path.join(project, filepath, filename)
    os.remove(replace_path)
    shutil.copyfile(os.path.join(CONFIG_DIR, project, filename), replace_path)


class Project:

    def __init__(self, clone_url, directory, env, debug):
        self.clone_url = clone_url
        self.directory = directory
        self.analyze_jars = ""
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


class GadgetInspector(Project):

    def __init__(self, debug):
        name = "gadgetinspector"
        env = os.environ.copy()
        env["JAVA_HOME"] = JDK_8
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)
        self.java_bin = os.path.join(JDK_8, "bin", "java")

    def setup(self):
        Project.setup(self)
        proc = subprocess.run(["./gradlew", "shadowJar"], cwd=self.directory, env=self.env, stdout=subprocess.PIPE, text=True)
        if self.debug:
            print(proc.stdout)
        if "BUILD SUCCESS" in proc.stdout:
            print(f"[INFO] {self.directory} built successfully")
        else:
            print(f"[ERROR] Build error in {self.directory}")

    def run(self):
        print("[INFO] Running GadgetInspector")
        Project.run(self)
        shutil.copyfile(os.path.join("analyze_targets", self.analyze_jars), os.path.join(self.directory, self.analyze_jars))
        proc = subprocess.run([self.java_bin, "-Xmx2G", "-jar", os.path.join("build", "libs", "gadget-inspector-all.jar"),
                         self.analyze_jars], cwd=self.directory, env=self.env, stdout=subprocess.PIPE, text=True)
        if self.debug:
            print(proc.stdout)
        for line in proc.stdout.split("\n"):
            if "[INFO] Found" in line:
                print(line)

    def get_result(self) -> str:
        raw_result = open(os.path.join(self.directory, "gadget-chains.txt"), "r").read()
        chains = raw_result.split("\n\n")
        for chain in chains:
            print(chain)


class Serianalyzer(Project):
    def __init__(self, debug):
        name = "serianalyzer"
        env = os.environ.copy()
        env["JAVA_HOME"] = JDK_8
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)

    def setup(self):
        Project.setup(self)
        replace_file(self.directory, "pom.xml", "")
        replace_file(self.directory, "SerianalyzerConfig.java",
                     os.path.join("src", "main", "java", self.directory))
        proc = subprocess.run(["mvn", "clean", "package"], cwd=self.directory, env=self.env, text=True, stdout=subprocess.PIPE)
        if self.debug:
            print(proc.stdout)

        if "BUILD SUCCESS" in proc.stdout:
            print("[INFO] Serianalyzer built successfully")
        else:
            print("[ERROR] Serianalyzer failed build")

    def run(self):
        print("[INFO] Running SeriAnalyzer")
        proc = subprocess.run(["java", "-cp", os.path.join("analyze_targets", self.analyze_jars),  "-jar", os.path.join(self.directory, "target",
                                "serianalyzer-1.1.0-jar-with-dependencies.jar"), os.path.join("analyze_targets", self.analyze_jars)], text=True, stdout=subprocess.PIPE)
        if self.debug:
            print(proc.stdout)
        print(proc.args)

        # since serianalyzer only writes to stdout, store to file
        open(os.path.join(self.directory, "chains.txt"), "w").write(proc.stdout)

    def get_result(self):
        raw_results = open(os.path.join(self.directory, "chains.txt"), "r").read()
        print(raw_results)

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--setup", action='store_true')
    parser.add_argument("--run", action='store_true')
    parser.add_argument("-p", "--project", help="select specific project")
    parser.add_argument("--debug", action='store_true')
    parser.add_argument("--jar", nargs=1, help="analyze a different jar file than gleipnier, ... beware however, that the algos are modified to look for only Controller.invokeSink")
    args = parser.parse_args()

    if args.project:
        if args.project == "serianalyzer": projects = [Serianalyzer(args.debug)]
        elif args.project == "gadgetinspector" : projects = [GadgetInspector(args.debug)]
    else:
        projects = [Serianalyzer(args.debug), GadgetInspector(args.debug)]

    if args.setup:
        for p in projects:
            p.clone()
            p.setup()

    if args.run:
        for p in projects:
            for file in os.listdir("analyze_targets"):
                p.analyze_jars = file
                p.run()
                p.get_result()



    return 0


if __name__ == "__main__":
    main()
