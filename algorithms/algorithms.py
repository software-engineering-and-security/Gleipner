import datetime
import json
import os
import subprocess
import shutil
import argparse
import re
import timeit
import time

from neo4j import GraphDatabase

##### CONFIG #####

# commit hashes preceded incase these prototypes get updated (seems unlikely though)
GITHUB_PROJECTS = {
    "serianalyzer": ["https://github.com/mbechler/serianalyzer.git",                 "85cff0a82d6684533fbc7ae6800e9e5ce40f6da0"],
    "gadgetinspector": ["https://github.com/JackOfMostTrades/gadgetinspector.git",   "ac7832d4220b50e9e1d9855b5cc21936466b33e6"],
    "serhybridpub": ["https://bitbucket.org/unshorn/serhybridpub.git",               "af6cb5a92f41f92212e9c3e87fc6941294e6cc5b"],
    "JChainz": ["https://github.com/Kigorky/JChainz.git",                            "52632b215d89977143cd5cde93757f9c0f168a6f"],
    "Crystallizer": ["https://github.com/HexHive/Crystallizer.git",                  "6a0eb336d2f75a28200311136593654900c1e99a"],
    "tabby": ["https://github.com/wh1t3p1g/tabby.git",                               "b3fa31ffb4fc68a6988ed10a91679d17b883146d"],
    "JDD" : ["https://github.com/fdu-sec/JDD.git",                                   "55299879170b90e6b57634b2932cecd71d559446"]
}

# An older JDK is required to execute gradle script in gadgetinspector
JDK_8 = "/home/jvm/oracle/8/jdk1.8.0_221"
JDK_11 = "/home/jvm/openjdk/11/jdk-11.0.2"
GRADLE_7 = "/opt/gradle/7.1.1/bin/gradle"
CONFIG_DIR = "_config"
OUTPUT_DIR = "_output"
TIME_FILE = "perf.log"


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
        proc = subprocess.run(["git", "clone", self.clone_url[0]], stdout=subprocess.PIPE, text=True)
        if self.debug:
            print(proc.stdout)
        proc = subprocess.run(["git", "checkout", self.clone_url[1]], cwd=self.directory, stdout=subprocess.PIPE, text=True)
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


class GadgetInspector(Project):

    def __init__(self, debug):
        name = "gadgetinspector"
        env = os.environ.copy()
        env["JAVA_HOME"] = JDK_8
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)
        self.java_bin = os.path.join(JDK_8, "bin", "java")

    def setup(self):
        Project.setup(self)
        replace_file(self.directory, "GadgetChainDiscovery.java", os.path.join("src", "main", "java", self.directory))
        proc = subprocess.run(["./gradlew", "shadowJar"], cwd=self.directory, env=self.env, stdout=subprocess.PIPE,
                              text=True)
        if self.debug:
            print(proc.stdout)
        if "BUILD SUCCESS" in proc.stdout:
            print(f"[INFO] {self.directory} built successfully")
        else:
            print(f"[ERROR] Build error in {self.directory}")


    def run(self):

        Project.run(self)
        base_file_name = os.path.split(self.analyze_jars[0])[-1]

        shutil.copyfile(self.analyze_jars[0], os.path.join(self.directory, base_file_name))

        t_start = timeit.default_timer()
        proc = subprocess.run(
            [self.java_bin, "-Xmx2G", "-jar", os.path.join("build", "libs", "gadget-inspector-all.jar"),
             base_file_name], cwd=self.directory, env=self.env, stdout=subprocess.PIPE, text=True)
        t_duration = timeit.default_timer() - t_start
        log_time(t_duration, self.directory)

        if self.debug:
            print(proc.stdout)
        for line in proc.stdout.split("\n"):
            if "[INFO] Found" in line:
                print(line)

    def get_result(self) -> str:
        raw_result = open(os.path.join(self.directory, "gadget-chains.txt"), "r").read()
        chains = raw_result.split("\n\n")
        result = ""

        for chain in chains:
            result += chain + "\n"

        return result


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
        proc = subprocess.run(["mvn", "clean", "package"], cwd=self.directory, env=self.env, text=True,
                              stdout=subprocess.PIPE)
        if self.debug:
            print(proc.stdout)

        if "BUILD SUCCESS" in proc.stdout:
            print("[INFO] Serianalyzer built successfully")
        else:
            print("[ERROR] Serianalyzer failed build")

    def run(self):

        t_start = timeit.default_timer()
        proc = subprocess.run(["java", "-cp", self.analyze_jars[0], "-jar", os.path.join(self.directory, "target",
                                "serianalyzer-1.1.0-jar-with-dependencies.jar"), self.analyze_jars[0]], text=True, stdout=subprocess.PIPE)
        t_duration = timeit.default_timer() - t_start
        log_time(t_duration, self.directory)

        if self.debug:
            print(proc.stdout)

        # since serianalyzer only writes to stdout, store to file
        open(os.path.join(self.directory, "chains.txt"), "w").write(proc.stdout)

    def get_result(self):
        raw_results = open(os.path.join(self.directory, "chains.txt"), "r").read()
        return raw_results


class JChainz(Project):

    def __init__(self, debug=False):
        name = "JChainz"
        env = os.environ.copy()
        env["JAVA_HOME"] = JDK_8
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)
        self.target_jar_dir = os.path.join(self.directory, "ChainzFinder", "target_jars")
        self.java_bin = os.path.join(JDK_8, "bin", "java")

    def check_build_ok(self, stdout, message):
        if self.debug:
            print(stdout)
        if "BUILD SUCCESS" in stdout:
            print(f"[INFO] {message} successfull")
        else:
            print(f"[ERROR] {message} failed")

    def setup(self):
        Project.setup(self)

        proc = subprocess.run(["mvn", "clean", "install"], cwd=os.path.join(self.directory, "ChainzFinder"),
                              env=self.env, text=True, stdout=subprocess.PIPE)
        self.check_build_ok(proc.stdout, "ChainzFinder compile")
        proc = subprocess.run(["mvn", "assembly:assembly", "-DdescriptorId=jar-with-dependencies"],
                              cwd=os.path.join(self.directory, "ChainzFinder"), env=self.env, text=True,
                              stdout=subprocess.PIPE)
        self.check_build_ok(proc.stdout, "ChainzFinder build")
        # clean the target_jar folder
        shutil.rmtree(self.target_jar_dir)
        os.mkdir(self.target_jar_dir)

        proc = subprocess.run(["mvn", "clean", "install"], cwd=os.path.join(self.directory, "ChainzAnalyzer"),
                              env=self.env, text=True, stdout=subprocess.PIPE)
        self.check_build_ok(proc.stdout, "ChainzAnalyzer compile")
        proc = subprocess.run(["mvn", "assembly:assembly", "-DdescriptorId=jar-with-dependencies"],
                              cwd=os.path.join(self.directory, "ChainzAnalyzer"), env=self.env, text=True,
                              stdout=subprocess.PIPE)
        self.check_build_ok(proc.stdout, "ChainzAnalyzer build")

    def run(self):

        shutil.rmtree(self.target_jar_dir, ignore_errors=True)
        os.mkdir(self.target_jar_dir)
        if os.path.exists(os.path.join(self.directory, "ChainzFinder", "config", "entryExitPoints.txt")):
            os.remove(os.path.join(self.directory, "ChainzFinder", "config", "entryExitPoints.txt"))

        base_file_name = os.path.split(self.analyze_jars[0])[-1]
        shutil.copyfile(self.analyze_jars[0], os.path.join(self.target_jar_dir, base_file_name))

        shutil.rmtree(os.path.join(self.directory, "ChainzFinder", "output"), ignore_errors=True)
        shutil.rmtree(os.path.join(self.directory, "ChainzFinder", "out"), ignore_errors=True)

        max_concurrent_finders = "2"
        max_chain_depth = "25"
        max_heap_size_gb = "8"
        intermediate_serialization_check = "true"
        sink_method = "gleipner.core.SinkGadget.sinkMethod"
        project_home = os.path.join(os.getcwd(), self.directory)
        max_chains = "100"
        timeout_seconds = "60"
        read_object_only = "true"

        t_total_start = timeit.default_timer()
        # 1. run ChainzFinder
        subprocess.run([self.java_bin, '-cp',
                        f'target/ChainzFinder-1.0-SNAPSHOT-jar-with-dependencies.jar:target_jars/{base_file_name}',
                        'chainz.ChainzFinderRunnerMain', max_concurrent_finders, max_chain_depth, max_heap_size_gb,
                        intermediate_serialization_check, sink_method, project_home, max_chains, timeout_seconds,
                        read_object_only], cwd=os.path.join(self.directory, "ChainzFinder"), env=self.env)
        t_duration = timeit.default_timer() - t_total_start
        log_time(t_duration, self.directory, "ChainzFinder")

        # 2. run ChainzAnalyzer
        # config setup
        with open(os.path.join(CONFIG_DIR, self.directory, "config.properties"), "r") as config_file:
            content = config_file.read()
            content = content.replace("$JAVA_HOME", JDK_8)
            content = content.replace("$PROJECT_HOME", os.path.join(os.getcwd(), self.directory))
            content = content.replace("$TARGET_JAR_PATH",
                                      os.path.join(os.getcwd(), self.target_jar_dir, base_file_name))
            with open(os.path.join(self.directory, "ChainzAnalyzer", "config.properties"), "w") as out_config_file:
                out_config_file.write(content)

        # clean output
        shutil.rmtree(os.path.join(self.directory, "ChainzAnalyzer", "output"), ignore_errors=True)



        # calling python script from python script because we can't have the import at the beginning before project is set up
        t_start = timeit.default_timer()

        finder_out_path = os.path.join(os.getcwd(), self.directory, "ChainzFinder", "output", "chains")
        proc = subprocess.run(["python3", "runner.py", finder_out_path],
                              cwd=os.path.join(self.directory, "ChainzAnalyzer"), stdout=subprocess.PIPE, text=True)

        t_duration = timeit.default_timer() - t_start
        log_time(t_duration, self.directory, "ChainzAnalyzer")
        log_time(timeit.default_timer() - t_total_start, self.directory)

    def get_result(self):
        # ChainzAnalyzer culls many chains found by ChainzFinder, this distinction is marked by the not_exploitable.flag in the individual chain folders
        analyzer_output_dir = os.path.join(self.directory, "ChainzAnalyzer", "output")
        result = ""
        if not os.path.exists(analyzer_output_dir):
            return result

        # 1. outer loop over all entry points
        # 2. all entry point methods
        # 3. the individual found chains
        for entry_point_dir in os.listdir(analyzer_output_dir):
            for entry_point_method_dir in os.listdir(os.path.join(analyzer_output_dir, entry_point_dir)):
                for chain_dir in os.listdir(os.path.join(analyzer_output_dir, entry_point_dir, entry_point_method_dir)):
                    chain_dir_path = os.path.join(analyzer_output_dir, entry_point_dir, entry_point_method_dir,
                                                  chain_dir)
                    if "exploitable.flag" in os.listdir(chain_dir_path):
                        chain = open(os.path.join(chain_dir_path, "chain.txt"), "r").read()
                        result += chain
        return result

class SerHybrid(Project):
    def __init__(self, debug=False):
        name = "serhybridpub"
        env = os.environ.copy()
        env["JAVA_HOME"] = JDK_8
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)

        self.java_bin = os.path.join(JDK_8, "bin", "java")
        self.jar_bin = os.path.join(JDK_8, "bin", "jar")

    def setup_randoop(self):
        # randoop is built using an older verison of JDK

        randoop_build_args = ['./gradlew', 'build']
        for exclude_task in ['systemTest', 'coveredTest', 'test', 'replacecallTest', 'html5validatorExists']:
            randoop_build_args.append('--exclude-task')
            randoop_build_args.append(exclude_task)

        subprocess.run(randoop_build_args, cwd=os.path.join(self.directory, 'randoop'), env=self.env)

    def setup(self):
        Project.setup(self)

        replace_file(self.directory, 'Sinks.java',
                     os.path.join('instrumenter', 'src', 'main', 'java', 'instrumenter', 'sinks'))
        replace_file(self.directory, 'Transform.java',
                     os.path.join('instrumenter', 'src', 'main', 'java', 'instrumenter'))
        replace_file(self.directory, 'Trampolines.java',
                     os.path.join('instrumenter', 'src', 'main', 'java', 'instrumenter', 'trampolines'))
        # custom dl has 2 missing imports, replace with repaired file
        replace_file(self.directory, 'custom.dl', os.path.join('datalog-souffle', 'custom'))
        replace_file(self.directory, 'sinks.txt', 'instrumenter')
        replace_file(self.directory, 'pom.xml', 'instrumenter')

        # We need to modify the import-sinks datalog script to point to where the sink definitions will be kept.
        # Note that we retrieve the sink definitions dynamically from log.log output, this will be copied after the instrumentation run

        import_sink_file = os.path.join(CONFIG_DIR, self.directory, "import-sinks.dl")
        export_sink_file = os.path.join(self.directory, "datalog-souffle", "custom", "import-sinks.dl")
        with open(import_sink_file, "r") as file_in:
            content = file_in.read().replace("$SINK_PATH",
                                             f'"{os.path.join(os.getcwd(), self.directory, "sinks.csv")}"')
            with open(export_sink_file, "w") as file_out:
                file_out.write(content)

        for build_dir in ["instrumenter", "java-analysis"]:
            proc = subprocess.run(['mvn', 'clean', 'install', 'dependency:copy-dependencies'],
                                  cwd=os.path.join(self.directory, build_dir), env=self.env, stdout=subprocess.PIPE,
                                  text=True)
            if self.debug:
                print(proc.stdout)
            if "BUILD SUCCESS" in proc.stdout:
                print(f"[INFO] Build of {build_dir} successfull")
            else:
                print(f"[ERROR] Build of {build_dir} failed")

        # Requires doop, so clone it on same commit id as publication and build it
        if os.path.exists(os.path.join(self.directory, "doop")):
            print(f"[INFO] Doop already cloned, delete doop subdirectory in {self.directory} to clone anew")
        else:
            print(f"[INFO] Cloning Doop at commit ba731a63c90e94f9e94afca39ff15c5082bf868d")
            subprocess.run(['git', 'clone', 'https://bitbucket.org/yanniss/doop.git'], cwd=os.path.join(self.directory),
                           stdout=subprocess.DEVNULL)
            subprocess.run(['git', 'checkout', 'ba731a63c90e94f9e94afca39ff15c5082bf868d'],
                           cwd=os.path.join(self.directory, 'doop'), stdout=subprocess.DEVNULL)

        self.setup_randoop()

    def run(self):

        # 1. Create instrumented jar file from jar to analyze
        shutil.rmtree(os.path.join(self.directory, 'instrumenter', 'input'), ignore_errors=True)
        shutil.rmtree(os.path.join(self.directory, 'instrumenter', 'output'), ignore_errors=True)
        os.mkdir(os.path.join(self.directory, 'instrumenter', 'input'))
        os.mkdir(os.path.join(self.directory, 'instrumenter', 'output'))

        base_file_name = os.path.split(self.analyze_jars[0])[-1]

        shutil.copyfile(self.analyze_jars[0],
                        os.path.join(self.directory, 'instrumenter', 'input', base_file_name))

        t_start_total = timeit.default_timer()
        proc = subprocess.run(
            [self.java_bin, '-cp', 'target/instrumenter-1.0.0.jar:target/dependencies/*', 'instrumenter.Transform',
             '--in', 'input', '--out', 'output', '--log', 'log.log', '--sinks', 'sinks.txt'],
            cwd=os.path.join(self.directory, 'instrumenter'), stdout=subprocess.PIPE, stderr=subprocess.STDOUT,
            text=True)
        log_time(timeit.default_timer() - t_start_total, "SerHybrid", "instrumenter")
        if self.debug:
            print(proc.stdout)
        print("[INFO] SerHybrid Instrumentation complete using Trampolines.java as entry points")

        output_base_path = os.path.join(self.directory, 'instrumenter', 'output', 'instrumenter')
        shutil.rmtree(os.path.join(output_base_path), ignore_errors=True)

        for dir in ['logger', 'checkers', 'sinks', 'trampolines']:
            shutil.copytree(os.path.join(self.directory, 'instrumenter', 'target', 'classes', 'instrumenter', dir),
                            os.path.join(output_base_path, dir))

        analyze_jar_name = base_file_name.replace(".jar", "")
        proc = subprocess.run(f'{self.jar_bin} cvf {analyze_jar_name}-instrumented.jar *',
                              cwd=os.path.join(self.directory, 'instrumenter', 'output'), shell=True,
                              stdout=subprocess.PIPE, text=True)
        shutil.copyfile(os.path.join(self.directory, "instrumenter", "log.log"),
                        os.path.join(self.directory, "sinks.csv"))

        if self.debug:
            print(proc.stdout)

        # 2. run doop (added --Xextra-logic switch, since otherwise the custom logic is not found for execution)
        # and apply custom doop-rules used for analysis
        for open_prog in ['rules-ser.dl', 'rules-all.dl', 'entry_points.dl']:
            shutil.copy(os.path.join(self.directory, 'datalog-souffle', 'open-programs', open_prog),
                        os.path.join(self.directory, 'doop', 'souffle-logic', 'addons', 'open-programs', open_prog))

        for file in os.listdir(os.path.join(self.directory, 'datalog-souffle', 'custom')):
            shutil.copy(os.path.join(self.directory, 'datalog-souffle', 'custom', file),
                        os.path.join(self.directory, 'doop', 'souffle-logic', 'addons', 'open-programs', file))

        for file in os.listdir(os.path.join(self.directory, 'datalog-souffle', 'reflection')):
            shutil.copy(os.path.join(self.directory, 'datalog-souffle', 'reflection', file),
                        os.path.join(self.directory, 'doop', 'souffle-logic', 'main', 'reflection', file))

        t_start = timeit.default_timer()
        proc = subprocess.run(
            ['./doop', '-i', os.path.join('..', 'instrumenter', 'output', f'{analyze_jar_name}-instrumented.jar'), '-a',
             'context-insensitive',
             '-id', f'{analyze_jar_name}', '--light-reflection-glue', '--Xstats-none', '--open-programs', 'all',
             '--Xextra-logic',
             os.path.join("..", "datalog-souffle", "custom", "custom.dl")], cwd=os.path.join(self.directory, "doop"),
            env=self.env, text=True)
        log_time(timeit.default_timer() - t_start, "SerHybrid", "doop")

        if self.debug:
            print(proc.stdout)
        if proc.stdout and "BUILD SUCCESS" in proc.stdout:
            print("[INFO] Doop successfully executed")

        # 3. copy to out and run java-analysis
        doop_db_dir = os.path.join(self.directory, "doop", "out", "context-insensitive", analyze_jar_name, "database")
        out_dir = os.path.join(self.directory, "out", analyze_jar_name)
        os.makedirs(out_dir, exist_ok=True)
        for file in os.listdir(doop_db_dir):
            if file in ["CallGraphEdge.csv", "ReachableSinks.csv", "ReachableTaintedSinks.csv", "SinkVariable.csv",
                        "FlowPath.csv", "AIPT.csv", "IPT.csv", "FlowPathVarTypes.csv"]:
                if os.path.exists(os.path.join(doop_db_dir, file)):
                    shutil.copyfile(os.path.join(doop_db_dir, file), os.path.join(out_dir, file))

        for file in ["strings.txt", "classes.txt", "SourceVariable.csv"]:
            shutil.copyfile(os.path.join(self.directory, file), os.path.join(out_dir, file))

        t_start = timeit.default_timer()
        subprocess.run(
            [self.java_bin, "-Xmx4G", "-cp", "target/analysisr-1.0.0.jar:target/dependencies/*", "analysis.main.Main",
             "--out", os.path.join('..', 'out', analyze_jar_name),
             '--log', 'log.log', '--t', 'heapinstance'], cwd=os.path.join(self.directory, "java-analysis"))
        log_time(timeit.default_timer() - t_start, "SerHybrid", "analysisr")

        # 4. run randoop
        input_jar_name = f"{analyze_jar_name}-instrumented.jar"

        t_start = timeit.default_timer()
        subprocess.run([self.java_bin, '-cp',
                        f'{os.path.join("randoop", "build", "libs", "randoop-all-4.2.2.jar")}:{os.path.join("instrumenter", "output", input_jar_name)}',
                        "randoop.main.Main", "gentests", "--testjar",
                        os.path.join("instrumenter", "output", input_jar_name), "--capture-output=true",
                        "--omit-classes-file=scripts/omitted-classes.txt", "--no-regression-tests",
                        "--log=randoop-log.txt", "--usethreads=true", "--time-limit=60", "--clear=15"], env=self.env,
                       cwd=self.directory)
        log_time(timeit.default_timer() - t_start, "SerHybrid", "randoop")
        log_time(timeit.default_timer() - t_start_total, "SerHybrid")

    def get_result(self):

        result = ""
        for file in os.listdir(os.path.join(self.directory, "out")):
            results_file_path = os.path.join(self.directory, "out", file, "hp.txt")
            chains = open(results_file_path, "r").readlines()
            for chain in chains:
                gadgets = re.findall("<[^<]*>", chain)
                for gadget in gadgets:
                    result += gadget + "\n"

                result += "\n"
        return result


class Crystallizer(Project):
    def __init__(self, debug=False):
        name = "Crystallizer"
        env = os.environ.copy()
        env["JAVA_HOME"] = JDK_11
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)

    def setup(self):
        Project.setup(self)
        subprocess.run(["docker", "pull", "prashast94/crystallizer:latest"])
        if not os.path.exists(os.path.join(self.directory, "results")):
            os.mkdir(os.path.join(self.directory, "results"))
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "SinkAnalysis.java"),
                       os.path.join(self.directory, "targets", "SinkAnalysis.java"))
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "LibSpecificRules.java"),
                        os.path.join(self.directory, "targets", "LibSpecificRules.java"))
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "run_campaigns.sh"),
                        os.path.join(self.directory, "targets", "run_campaigns.sh"))
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "potential_sinks"),
                        os.path.join(self.directory, "targets", "potential_sinks"))

        subprocess.run(["docker", "run", "--security-opt", "seccomp=unconfined", "--name", "crystallize_test", "-v",
                        f"{os.path.join(os.getcwd(), self.directory)}/targets:/root/SeriFuzz/targets", "-v",
                        f"{os.path.join(os.getcwd(), self.directory)}/results:/root/SeriFuzz/results", "--rm",
                         "-t", "-d", "prashast94/crystallizer:latest"])

    def run(self):
        if not os.path.exists(os.path.join(self.directory, "targets", "gleipner")):
            os.mkdir(os.path.join(self.directory, "targets", "gleipner"))

        base_file_name = os.path.split(self.analyze_jars[0])[-1]
        shutil.copyfile(self.analyze_jars[0], os.path.join(self.directory, "targets", "gleipner", "gleipner.jar"))
        print("Copy command into console:")
        dynamic_sink_id_time = "60"
        concretization_time = "60"
        print(
            f"eval/run_campaigns.sh /root/SeriFuzz/targets/gleipner/gleipner.jar {dynamic_sink_id_time} {concretization_time}")

        # copy lib specific rules with uncommented hashCode into container
        subprocess.run(["docker", "exec", "crystallize_test", "rm", "-r", "/root/SeriFuzz/results/concretization/crashes"])
        t_start_total = timeit.default_timer()
        subprocess.run(["docker", "exec", "crystallize_test", "cp", "/root/SeriFuzz/targets/LibSpecificRules.java", "/root/SeriFuzz/src/static/src/main/java/analysis/LibSpecificRules.java"])

        # comment out below lines if you don't want to short-circuit dynamic sink identification
        subprocess.run(["docker", "exec", "crystallize_test", "cp", "/root/SeriFuzz/targets/SinkAnalysis.java", "/root/SeriFuzz/src/static/src/main/java/analysis/SinkAnalysis.java"])
        subprocess.run(["docker", "exec", "crystallize_test", "cp", "/root/SeriFuzz/targets/run_campaigns.sh", "/root/SeriFuzz/eval/run_campaigns.sh"])

        subprocess.run(["docker", "exec", "crystallize_test", "./eval/run_campaigns.sh", "/root/SeriFuzz/targets/gleipner/gleipner.jar", dynamic_sink_id_time, concretization_time])
        log_time(timeit.default_timer() - t_start_total, self.directory)

        with open(os.path.join(self.directory, "results", "concretization", "candidate_paths"), "r") as paths_file:
            do_skip = True
            for line in paths_file.readlines():
                if "===" in line:
                    do_skip = False
                if do_skip or line.strip() == "":
                    continue
                print(line)

        subprocess.run(
            ["docker", "exec", "crystallize_test", "python", "./eval/concretized_paths.py", "--concretized_ids",
             "/root/SeriFuzz/results/concretization/crashes", "--paths",
             "/root/SeriFuzz/results/concretization/candidate_paths"])
        subprocess.run(["docker", "exec", "crystallize_test", "cp", "./eval/concretized_paths.json",
                        "/root/SeriFuzz/results/concretized_paths.json"])

        #subprocess.run(["docker", "exec", "-it",  "crystallize_test", "bash"])

    def get_result(self):
        with open(os.path.join(self.directory, "results", "concretized_paths.json"), "r") as results_file:
            return results_file.read()


class Tabby(Project):

    def __init__(self, debug=False):
        name = "tabby"
        env = os.environ.copy()
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)

    def setup(self):
        Project.setup(self)

        # Clone tabby-vuln-finder, build and cp back to root directory
        vul_finder_url = "https://github.com/wh1t3p1g/tabby-vul-finder.git"
        #subprocess.run(["git", "clone", vul_finder_url], cwd=self.directory)
        #subprocess.run(["mvn", "clean", "package", "-DskipTests"], cwd=os.path.join(self.directory, "tabby-vul-finder"))
        #shutil.copyfile(os.path.join(self.directory, "tabby-vul-finder", "target", "tabby-vul-finder.jar"),
        #               os.path.join(self.directory, "tabby-vul-finder.jar"))

        # import db.properties
        db_config = "\n".join(
            ["tabby.cache.isDockerImportPath = true", "tabby.neo4j.username = neo4j", "tabby.neo4j.password = password",
             "tabby.neo4j.url = bolt://127.0.0.1:7687"])

        with open(os.path.join(self.directory, "config", "db.properties"), "w") as db_config_file:
            db_config_file.write(db_config)
        # import tabby-vuln-finder rules
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "cyphers.yml"), os.path.join(self.directory, "rules", "cyphers.yml"))

        # update tabby settings.properties, sink list
        replace_file(self.directory, "settings.properties", "config")
        replace_file(self.directory, "sinks.json", "rules")

        # build tabby
        subprocess.run(["./gradlew", "bootJar"], cwd=self.directory)

        # clone version 1.0 of tabby-path-finder
        #path_finder_url = "https://github.com/wh1t3p1g/tabby-path-finder.git"
        #subprocess.run(["git", "clone", "--branch", "v1.0.0", path_finder_url], cwd=self.directory)
        #subprocess.run(["mvn", "clean", "package", "-DskipTests"],
        #              cwd=os.path.join(self.directory, "tabby-path-finder"))
        # os.replace(os.path.join(self.directory, "tabby-path-finder", "tabby-path-finder-1.0.jar"), os.path.join(self.directory, "env", "tabby-path-finder-1.1.jar"))

        # build and run docker-compose (bg)
        if not os.path.exists(os.path.join(self.directory, "env", "import")):
            os.mkdir(os.path.join(self.directory, "env", "import"))
        subprocess.run(["docker-compose", "up", "-d"], cwd=os.path.join(self.directory, "env"))


    def run_path_finder(self, driver, entry_point):
        records, _, _ = driver.execute_query(
            'match (source:Method) '
            'WHERE ANY (n IN source.NAME WHERE n IN ["readObject", "readObjectNoData", "readResolve", "readExternal"])'
            'match (sink:Method {IS_SINK: true} ) '
            'call apoc.algo.allSimplePaths(sink, source, "<", 15) yield path '
            'return nodes(path), relationships(path)', database_="neo4j"
        )
        return records

    def run(self):
        # ! really run --setup and --run separately, otherwise there will be access issues with the import dir

        subprocess.run(["docker", "exec", "env-db-1", "chmod", "-R", "777", "/var/lib/neo4j/import"])

        base_file_name = os.path.split(self.analyze_jars[0])[-1]
        shutil.copyfile(self.analyze_jars[0], os.path.join(self.directory, base_file_name))

        # configure jar to analyze in settings.properties file
        settings_file = os.path.join(self.directory, "config", "settings.properties")
        new_content = []
        with open(settings_file, "r") as f:
            for line in f.readlines():
                if "tabby.build.target" in line:
                    new_content.append(f"tabby.build.target                        = {base_file_name}\n")
                else:
                    new_content.append(line)
        with open(settings_file, "w") as f:
            f.writelines(new_content)

        t_start_total = timeit.default_timer()
        subprocess.run(["java", "-jar", "build/libs/tabby.jar"], cwd=self.directory)
        log_time(timeit.default_timer() - t_start_total, self.directory, "build-dacg")

        # move files to import directory of neo4j
        output_dir =os.path.join(self.directory, "output", "dev")
        for file in os.listdir(output_dir):
            shutil.copyfile(os.path.join(output_dir, file), os.path.join(self.directory, "env", "import", file))

        t_start = timeit.default_timer()
        subprocess.run(["java", "-jar", "tabby-vul-finder.jar", "load", "output/dev"], cwd=self.directory)
        log_time(timeit.default_timer() - t_start, self.directory, "import-dacg")

        # query tabby db
        out_file = open(os.path.join(self.directory, "chains.txt"), "w")

        uri = "neo4j://localhost:7687"
        driver = GraphDatabase.driver(uri, auth=("neo4j", "password"), max_connection_lifetime=100)

        t_start = timeit.default_timer()
        records = self.run_path_finder(driver, "readObject")
        log_time(timeit.default_timer() - t_start, self.directory, "query-dacg")
        log_time(timeit.default_timer() - t_start_total, self.directory)

        for record in records:
            nodes = record["nodes(path)"]
            out_file.write("[\n")
            for node in nodes:
                out_file.write(node['SIGNATURE'] + "\n")
            out_file.write("]\n\n")
        driver.close()

    def get_result(self):
        with open(os.path.join(self.directory, "chains.txt"), "r") as results_file:
            return results_file.read()

        #p = subprocess.run(["java", "-jar", "evaluator.jar", os.path.join(self.directory, "chains.txt"), self.analyze_jars[0], self.directory])

class JDD(Project):

    def __init__(self, debug):
        name = "JDD"
        env = os.environ.copy()
        env["JAVA_HOME"] = JDK_8
        Project.__init__(self, GITHUB_PROJECTS[name], name, env, debug)
        self.java_bin = os.path.join(JDK_8, "bin", "java")

    def setup_filestructure(self):
        resource_dir = os.path.join(self.directory, "jdd", "src", "main", "resources")
        src_dir = os.path.join(self.directory, "jdd", "src", "main", "java")

        # check if filestructure was already setup:
        if os.path.exists(src_dir):
            print("[INFO] filestructure already setup ... proceeding to compilation")
            return

        os.makedirs(src_dir)
        os.mkdir(resource_dir)

        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "pom.xml"),
                        os.path.join(self.directory, "jdd", "pom.xml"))
        shutil.copyfile(os.path.join(CONFIG_DIR, self.directory, "log4j.properties"),
                        os.path.join(resource_dir, "log4j.properties"))

        for dir in os.listdir(os.path.join(self.directory, "src", "jdd")):
            dir_path = os.path.join(self.directory, "src", "jdd", dir)
            if os.path.isdir(dir_path):
                shutil.copytree(dir_path, os.path.join(src_dir, dir))

        for dir in os.listdir(os.path.join(self.directory, "src", "java")):
            dir_path = os.path.join(self.directory, "src", "java", dir)
            if os.path.isdir(dir_path):
                if dir in ["dataflow", "util"]:
                    for file in os.listdir(dir_path):
                        if os.path.isfile(os.path.join(dir_path, file)):
                            shutil.copyfile(os.path.join(dir_path, file), os.path.join(src_dir, dir, file))
                        else:
                            shutil.copytree(os.path.join(dir_path, file), os.path.join(src_dir, dir, file))
                else:
                    shutil.copytree(dir_path, os.path.join(src_dir, dir))



    def setup(self):
        Project.setup(self)

        # repair jdd src file structure to prepare for compilation
        # this is necessary because of messy src file copying in the artifact. The files in src/java and src/jdd
        # need to be merged into a common directory src/main/java
        self.setup_filestructure()
        replace_file(self.directory, "ConfigUtil.java", os.path.join("jdd", "src", "main", "java", "config"))
        replace_file(self.directory, "ExecCheckRule.java", os.path.join("jdd", "src", "main", "java", "rules", "sinks"))

        # copy config
        replace_file(self.directory, "config.properties", "config")
        if not os.path.exists(os.path.join(self.directory, "testExample", "Gleipner")):
            os.mkdir(os.path.join(self.directory, "testExample", "Gleipner"))

        proc = subprocess.run(["mvn", "clean", "compile", "assembly:single"], cwd=os.path.join(self.directory, "jdd"),
                              env=self.env, stdout=subprocess.PIPE, text=True)

        shutil.move(os.path.join(self.directory, "jdd", "target", "jdd-1.0-SNAPSHOT-jar-with-dependencies.jar"), os.path.join(self.directory, "jdd.jar"))

        if self.debug:
            print(proc.stdout)
        if "BUILD SUCCESS" in proc.stdout:
            print(f"[INFO] {self.directory} built successfully")
        else:
            print(f"[ERROR] Build error in {self.directory}")


    def run(self):

        Project.run(self)

        base_file_name = os.path.split(self.analyze_jars[0])[-1]
        shutil.copyfile(self.analyze_jars[0], os.path.join(self.directory, "testExample", "Gleipner", "gleipner.jar"))

        # empty the output directory:
        if os.path.exists(os.path.join(self.directory, "outputs", "gadgets", "Gleipner")):
          for file in os.listdir(os.path.join(self.directory, "outputs", "gadgets", "Gleipner")):
              os.remove(os.path.join(self.directory, "outputs", "gadgets", "Gleipner", file))

        # remove tmp dir, since otherwise the previous gleipner jar is still unpacked in the tmp dir and no new analysis is performed
        if os.path.exists(os.path.join(self.directory, "testExample", "Gleipnertmp")):
            shutil.rmtree(os.path.join(self.directory, "testExample", "Gleipnertmp"))

        t_start = timeit.default_timer()
        subprocess.run([self.java_bin , "-jar", "jdd.jar"], env=self.env, cwd=self.directory)
        t_duration = timeit.default_timer() - t_start
        log_time(t_duration, self.directory)


    def get_result(self) -> str:

        chains = ""
        for file in os.listdir(os.path.join(self.directory, "outputs", "gadgets", "Gleipner")):
            if "IfStmtRecord" in file or not ".json" in file:
                continue

            file_handle = open(os.path.join(self.directory, "outputs", "gadgets", "Gleipner", file), "r")
            result_json = json.load(file_handle)
            for gadget in result_json["gadgetCallStack"]:
                chains += gadget["value"] + "\n"
            chains += "\n"

        return chains

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
    args = parser.parse_args()

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
        elif args.project == "jdd":
            projects = [JDD(args.debug)]
    else:
        projects = [Serianalyzer(args.debug), GadgetInspector(args.debug), SerHybrid(args.debug), JChainz(args.debug),
                    Crystallizer(args.debug), Tabby(args.debug), JDD(args.debug)]

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
