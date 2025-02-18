# Gleipner

A benchmark for Java gadget chain detecting algorithms.

<img src="Gleipner.png" width="400" height="400">

*If you are thinking this image is AI-generated: it is.*

## Citation

Official publication will appear in the *Proceedings of FSE 2025*. For now, there is a preprint available on *hal.science*:
```bibtex
@inproceedings{kreyssig_gleipner_2025,
    author = {Kreyssig, Bruno and Bartel, Alexandre},
    title = {Gleipner - A Benchmark for Gadget Chain Detection in Java Deserialization Vulnerabilities},
    booktitle = {Preprint for the ACM International Conference on the Foundations of Software Engineering (FSE) 2025},
    year = 2025,
    url = {https://hal.science/hal-04890074}
}
```

## Usage

Gleipner is provided as a collection of JAR files, each probing different challenges in gadget chain detection (see Specification). 

For existing gadget chain detection algorithms see benchmarked algorithms. 
When benchmarking new tools, adjust the sinkGadget list to search for ``gleipner.core.SinkGadget.sinkMethod(String taint)``. This benchmark is sink-agnostic, so if you are using dynamic sink identification, we suggest to modify the [SinkGadget](chains/src/main/java/gleipner/core/SinkGadget.java) source code to emulate something which will be dynamically found.

To build the project from source (either):

```bash
# builds and copies all relevant files to algorithms/_target
make compile

# only build to target/chains
mvn clean package
```

Then, simply run your tool on the generated JAR files. There are two trivial gadget chains in ``gleipner.chains-<version>-basic.jar``; if your tool finds nothing in this jar, there is likely something wrong with the setup.

The amount of false and true positives per JAR are written as properties in the ``META-INF/MANIFEST.MF`` file (``chains-tp`` and ``chains-fp``). Whereas the individual gadget chains can be identified both through the ``@FalsePositive`` annotation and the ``_FP_`` infix in the class name. Feel free to use this meta information to create your own verification process or use the provided ``evaluator.jar``

```bash
java -jar evaluator.jar <output_file> [mode]

# generic format
java -jar evaluator.jar <output_file>
```

The ``mode`` uses a selection of predefined formats observed in other gadget chain detection tools. If you are creating a new tool, we suggest adhering to the basic format below. Multiple chains are separated by an empty line.

```
class;method;arg1;arg2

gleipner.chains.ysoserial.myfaces1.MyFaces_ValueExpressionMethodExpression;hashCode;
gleipner.chains.ysoserial.myfaces1.MyFaces_ValueExpressionMethodExpression;getMethodExpression;
gleipner.chains.ysoserial.myfaces1.MyFaces_ValueExpressionMethodExpression;getMethodExpression;Lgleipnir.chains.ysoserial.myfaces1.MyFaces_ELContext
gleipner.chains.ysoserial.myfaces1.MyFaces_ValueExpressionImpl;getValue;Lgleipnir.chains.ysoserial.myfaces1.MyFaces_ELContext
gleipner.core.Controller;invokeSink;Ljava.lang.String
```

## Specification

For a detailed understanding of how we chose the test cases and how they were implemented, see our publication.

### Synthetic Gadget Chains

- True Positives : **120**
- False Positives : **47**
- Categories : **17**

Note that the quantity difference of *true positives* to *false positives* is mainly due to the auto-generated categories (depth, polymorphism and multipath). 

For the true positive (TP) and false positive (FP) stated below, be aware that as this benchmark may evolve, the README may not always be the first thing to be updated. So double check the quantities in the ``pom.xml`` manifest properties.

#### Baseline tests

| Category | Jar File | TP | FP | Description |
| ----- | ----- | ----- | ----- | ----- |
| Depth | ``gleipner.chains-<version>-depth.jar`` | 20 | 0 | Gadget chains of varying depth ``Depth_001_TrampolineGadget.hashCode() --> Depth_001_LinkGadget1.linkMethod() --> ... --> Depth_001_SinkGadget.linkMethod()`` <br> the shortest is 3 gadgets in length, going up to 22. You can generate more using the scripts in [Chain generation scripts](#chain-gen). |
| Polymorphism | ``gleipner.chains-<version>-polymorphism.jar`` | 20 | 0 | In general ``Polymorphism_nnn_TrampolineGadget.hashCode()`` calls ``Polymorphism_nnn_Parent.linkMethod()``, which contains up to 2046 child classes (over multiple inheritance layers). Somewhere within these children there is a ``Polymorphism_nnn_LinkGadget`` which calls the ``Polymorphism_001_SinkGadget.linkMethod()`` ... containing the call to the Gleipner sink.  |
| Multipath | ``gleipner.chains-<version>-multipath.jar`` | 10 | 0 | Basically a big switch/case statement in the ``Multipath_001_TrampolineGadget`` calling 10 different link gadgets, all leading to Gleipner's sink method. |
| Payload Construction | ``gleipner.chains-<version>-payload-construction.jar`` | 9 | 6 | Refer to the 3 base classes for creating parallel and nested object payload structures in ``chains/src/main/java/gleipner/chains/taint/nested``. The test cases leverage this structure to create conditional structures requiring special crafting of objects using these 3 base classes. |
| Serialization variants | ``gleipner.chains-<version>-serialization-variants.jar`` | 4 | 0 | For now: all Java deserialization methods: ``readObject()``, ``readResolve()``, ``readObjectNoData()`` and ``readExternal()``. <br> ``readObjectNoData()`` is tricky in practice and we refer to the unit test in ``chains/src/test/java/gleipner/SerializationVariantsTests.java`` and the [Java Docs](https://docs.oracle.com/javase/6/docs/platform/serialization/spec/input.html#6053). This still doesn't mean it is irrelevant for a deserialization gadget chain, since library APIs may change in a way to make this work (serializing using an old library and then triggering ``readObjectNoData()`` through the new parent class).   |
| Serialization keywords | ``gleipner.chains-<version>-keywords.jar`` | 2 | 2 | Test if the tool cares about ``transient`` keywords and ``Serializable`` interface | 

#### Reflection tests

| Category | Jar File | TP | FP | Description |
| ----- | ----- | ----- | ----- | ----- |
| ``Method.invoke`` | ``gleipner.chains-<version>-reflection-methodinvoke.jar`` | 5 | 5 | - 001, 002_FP: the attacker has control over the method name, params, but not the ClassType (i.e., requires analyzing all methods in the ClassType as link gadgets)  <br> - 003, 004_FP: reflective static invocation (FP: the invocation target is virtual and thus Method.invoke throws an exception) <br> - 005, 006_FP: virtual reflective invocation <br> - 007, 008_FP: virtual reflective invocation with no args <br> - 009, 010_FP: reflection method.invoke not used as direct link, but must be analyzed to get return value of tainted param |
| ``<clinit>`` | ``gleipner.chains-<version>-reflection-clinit.jar`` | 2 | 2 | 4 examples of static method invocations being triggered in the ``static { ... }`` blocks of the link gadget classes. The entry point to be resolved is ``Class.forName("package.ClassName")`` which executes the static clinit in these link gadgets. |
| Classloading | ``gleipner.chains-<version>-reflection-classloading.jar`` | 2 | 1 | - 001, 002_FP: a custom classloader deserializing a class in b64 which contains a boolean static value; then test if this value is true (FP/TP respectively) <br> - 003: loads a class file from the resources directory and uses it as a link gadget |
| Constructor | ``gleipner.chains-<version>-reflection-constructor.jar`` | 3 | 3 | - 001, 002_FP: attacker has full control over which constructor is taken from a predetermined class by providing the paramTypes (requires iteration over all ctors as in Method.invoke 001, 002) <br> - 003, 004_FP: Constructor method is given, attacker has control over the args passed to it <br> - 005, 006_FP calling constructor is not a link gadget, but relevant to resolve parameters being set, for taint analysis |
| Dynamic Proxies | ``gleipner.chains-<version>-reflection-proxy.jar`` | 3 | 2 | - 001: direct invocation of an interface method in trampoline gadget, this can be resolved to the sink method in Proxy_001_InvocationHandler <br> - 002, 003_FP: here the proxy is not a link gadget but returns a tainted string value <br> - 004, 005_FP: twice nested proxy invocation like in Spring1 gadget chain |
| MetaObjects | ``gleipner.chains-<version>-reflection-metaobjects.jar`` | 11 | 10 | a diverse collection of conditional checks through MetaMethods such as: ``Class.getClasses()``, ``Class.getDeclaredFields()``, ``Class.getInterfaces()``, ``Class.getMethods()``, ``Class.getAnnotations()``, ``Proxy.isProxyClass()``, ``Class.getConstructors()``,  ``Field.isAccessible()``, ``Class.cast()``, ``Array.get()``, ``Array.set()`` |
| Exceptions | ``gleipner.chains-<version>-reflection-methodinvoke.jar`` | 7 | 7 | 1 FP and TP for each exception related to reflection: ``ClassCastException``, ``ClassNotFoundException``, ``IllegalAccessException``, ``InstantiationException``, ``InvocationTargetException``, ``NoSuchFieldException``, ``NoSuchMethodException``; the call to the sink method is always in the catch clause |

#### Other

| Category | Jar File | TP | FP | Description |
| ----- | ----- | ----- | ----- | ----- |
| Runtime Exceptions | ``gleipner.chains-<version>-exceptions.jar`` | 7 | 7 | Similar to the reflection exceptions - runtime exceptions with predictable (! **important for reproducibility**) triggers: ``ArithmeticException``, ``ArrayStoreException``, ``EmptyStackException``, ``IndexOutOfBoundsException``, ``NegativeArraySizeException``, ``NoSuchElementException``, ``NullPointerException``; i.e., static analysis can determine if the parameter causing the exception can be tainted, such that the catch clause is reached |
| JNI | ``gleipner.chains-<version>-jni.jar`` | 2 | 0 | one example backed by a native library (custom.so or custom_win.dll) in resources; one example with the native Thread.start call <br> *In case the native libraries are lost, in the Makefile the ``compile_so`` target rebuilds these two libraries from the cpp and header file in resources; requires g++ and mingw* |
| dynamicinvoke | ``gleipner.chains-<version>-lambda.jar`` | 1 | 1 | one FP and TP, defining an interface method through a lambda expression. These will be compiled into dynamic invocation sites |
| Ysoserial | ``gleipner.chains-<version>-ysoserial-<chain>.jar`` | 12 | 1 | see below [Implemented Real-World Gadget Chains](#ysoserial) |

### <a name="ysoserial"></a> Implemented Real-World Gadget Chains

| GadgetChain | Sink Gadget | Source | Challenges |
|-----|-----|-----|-----|
| AspectJWeaver | ``SinkGadget.sinkMethod()`` |Ysoserial| nested payload generation through ``ConstantTransformer`` |
|C3P0 | ``SinkGadget.sinkMethod()`` |Ysoserial| custom ``readObject()`` routine, payload generation requires custom ``ConnectionPoolDataSource`` being supplied to ``writeObject()`` |
| CommonsCollections1 |``Method.invoke()``|Ysoserial| proxy entry point, transformer chain for payload generation |
| Clojure |``SinkGadget.sinkMethod()`` |Ysoserial| nested payload generation |
| Groovy |``SinkGadget.sinkMethod()`` |Ysoserial| proxy entry point, reflection (untainted ``Method.invoke()``) |
| Hibernate1 |``SinkGadget.sinkMethod()`` |Ysoserial| link gadget restoring transient fields in custom ``readObject()`` routine |
| JRMPListener |``SinkGadget.sinkMethod()`` |Ysoserial| chain depth (9) |
| MyFaces1 |``SinkGadget.sinkMethod()`` | Ysoserial | reflection (``instanceof`` operator) |
|ROME|``Method.invoke()`` - getter |Ysoserial| reflection (partially tainted ``Method.invoke()``)  |
| Spring1 |``Method.invoke()`` - getter |Ysoserial| 3-times nested proxy, complex payload generation  |
|URLDNS |``SinkGadget.sinkMethod()`` |Ysoserial|  complex ``readObject()`` and ``readResolve()`` entry point combination|
|Vaadin1|``Method.invoke()`` - getter | Ysoserial | reflection (partially tainted ``Method.invoke()``) |

## <a name="chain-gen"></a> Chain generation scripts

If you are using the setup used in the original benchmark proceed with 

```bash
make generate_artificial
```

else, you can generate to your liking using:

```bash
cd scripts

# remove all existing depth, multipath and polymorphism chains
generate-chains.py --clean

# create multiple chains with varying depth from lower to upper
python3 generate-chains.py --depth <lower> <upper>

# create a polymorphism chain with x children per layer and y layers
python3 generate-chains.py --polymorphism <x> <y>

# generate a single multipath chain with <x> paths branching from one gadget; run only once 
python3 generate-chains.py --multipath 10

```

## Contributing and expanding the benchmark

- add new chains in ``chains/src/main/java/gleipner/chains``
  - for a new category add a new package here
  - for adding new mocked ysoserial chains add a new package underneath ``ysoserial``
  - use the ``_FP_`` and ``@FalsePositive`` annotation to flag chains, which should be recognized as false positives
  - ``TriggerGadgets`` extend ``GleipnerObject``; then override ``hashCode()`` to create your entry point
  - the chain should end in ``SinkGadget.sinkMethod(String)``
  - look at ``chains/src/main/java/gleipner/chains/basic/BasicTriggerGadget.java`` and ``chains/src/main/java/gleipner/chains/basic/BasicSinkGadget.java`` for a basic example
 
- when done, modify the ``pom.xml`` to create an additional jar file
  - replace ``mytest`` with your package name below and change ``chains-tp`` and ``chains-fp`` as applicable to the cound of true and false positives

```xml
<execution>
  <id>mytest</id>
  <goals>
    <goal>jar</goal>
  </goals>
  <configuration>
    <classifier>mytest</classifier>
    <includes>
      <include>**/core/**</include>
      <include>**/mytest/**</include>
    </includes>
    <archive>
      <manifestEntries>
        <chains-tp>1</chains-tp>
        <chains-fp>1</chains-fp>
      </manifestEntries>
    </archive>
  </configuration>
</execution>
```

## Artifact Evaluation / Benchmarked Algorithms

**Note: JDD has been recently updated (Feb 2025) and now fulfills the depth gadget chain category, aswell as the Ysoserial replicas: Hibernate, URLDNS and JRMPListener. Check out their [Repo](https://github.com/fdu-sec/JDD/tree/main) for more details.**

For the submission we ran our benchmark on the tools listed below. The raw results are in the [output](algorithms/_output) dir, configuration modifications in [config](algorithms/_config) dir:

- [GadgetInspector](https://github.com/JackOfMostTrades/gadgetinspector/tree/ac7832d4220b50e9e1d9855b5cc21936466b33e6) (2017 - Ian Haken)
- [SeriAnalyzer](https://github.com/mbechler/serianalyzer) (2017 - Moritz Bechler)
  - for setup modify pom.xml with missing ``javax.activation`` package, see [here](https://stackoverflow.com/questions/61367969/java-mvn-package-javax-activation-does-not-exist-and-mvn-install-not-solving-it)
  - also we add a mainClass to the manifest for the maven-assembly-plugin and update the asm package version to avoid errors
  - then ``mvn clean package``
  - sink methods are configured in ``GadgetChainDiscovery.java``
- [SerHybrid](https://bitbucket.org/unshorn/serhybridpub/src/master/) (2020 - Shawn Rasheed)
  - requires older version of Soufflé to be installed (v1.7.0), see below
- [Tabby](https://github.com/wh1t3p1g/tabby)
- [JChainz](https://github.com/Kigorky/JChainz/tree/main)
  - there is a bug in gadget chain detection in JChainz, where subtypes are removed in the ChainzAnalyzer stage; to fairly benchmark we need a modified version of Gleipner
  - created modified version of Gleipner using ``scripts/generate-chains.py``
  - or run ``make compile_alternate``
  - this basically replaces the hashCode entry point with readObject
- [Crystallizer](https://github.com/HexHive/Crystallizer)
  - short-circuit dynamic sink identification (see files in ``_config/Crystallizer``)
  - when running with ``algorithms.py`` you will have to ``docker exec -it <crystallizer_container> bash`` into the container after execution completed and get the results using:
  - ``cd eval && python concretized_paths.py --concretized_ids ~/SeriFuzz/results/concretization/crashes --paths ~/SeriFuzz/results/concretization/candidate_paths``
  - then ``cat concretized_paths.json``
  - see also: [Crystallizer README](https://github.com/HexHive/Crystallizer/tree/main)
- [JDD](https://github.com/fdu-sec/JDD/tree/main)

Within the ``algorithms`` folder we added a setup python script to clone, configure (sinks and to avoid version errors) and execute the algorithms on our benchmark.

```bash
cd algorithms
python3 algorithms.py -p <project> --setup --run --jar _target/<target_jar>

# p = crystallizer serhybrid serianalyzer gadgetinspector jchainz tabby
# after running you can view the results with:
python3 algorithms.py -p <project> --jar _target/<target_jar> --show
```

Make sure to modify the constants for ``JDK_8``, ``JDK_11`` and ``GRADLE_7`` in lines 23-25 of the ``algorithms.py`` script.


### SerHybrid Setup

The setup of SerHybrid is a bit tedious because of the package requirements of the older **Doop** version.

```bash
# 1. install older libffi version for souffle 1.7.0
# see https://stackoverflow.com/questions/61875869/ubuntu-20-04-upgrade-python-missing-libffi-so-6
wget https://mirrors.kernel.org/ubuntu/pool/main/libf/libffi/libffi6_3.2.1-8_amd64.deb
apt install ./libffi6_3.2.1-8_amd64.deb

# 2. then get souffle 1.7.0
wget https://github.com/souffle-lang/souffle/releases/download/1.7.0/souffle_1.7.0-1_amd64.deb
apt install ./souffle_1.7.0-1_amd64.deb

# ... now doop will not drop the annoying "Error: Atoms argument type is not a subtype of its declared type" errors
```

### Excluded

- [GCMiner](https://github.com/GCMiner/GCMiner/tree/main), incomplete source code, see [Issue](https://github.com/GCMiner/GCMiner/issues/1); [GCMiner publication](https://dl.acm.org/doi/abs/10.1109/ICSE48619.2023.00044)
- [HawkGadget](https://dl.acm.org/doi/abs/10.1145/3548608.3559310), no source code available
- [GCGM](https://ceur-ws.org/Vol-3330/Paper-02-QUASOQ.pdf), no source code available
- [ODDFuzz](https://arxiv.org/pdf/2304.04233.pdf), author contacted, will remain closed source until reviewed by AntGroup
- [RevGadget](https://link.springer.com/chapter/10.1007/978-3-031-53555-0_22), no source code available

