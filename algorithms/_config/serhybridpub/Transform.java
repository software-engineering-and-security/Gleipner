package instrumenter;

import instrumenter.utils.JarUtils;
import org.apache.commons.cli.*;
import org.apache.commons.io.output.NullOutputStream;
import org.objectweb.asm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Transform {

    private static Logger logger = LoggerFactory.getLogger(Transform.class);

    public static void main(String... args)  throws IOException, ParseException{

    Options options = new Options();



    options.addOption(Option.builder().longOpt("in").argName("file").desc("Input directory, which contains JAR files").hasArg().build());
    options.addOption(Option.builder().longOpt("out").argName("file").desc("Output directory to store transformed JARs into").hasArg().build());
    options.addOption(Option.builder().longOpt("log").argName("file").desc("Name of the log file").hasArg().build());
    options.addOption(Option.builder().longOpt("sinks").argName("file").desc("Name of the file with list of sinks").hasArg().build());

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);

    if (!cmd.hasOption("in") || !cmd.hasOption("out") || !cmd.hasOption("sinks")) {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("instrumenter", options);

        return;
    }

    OutputStream logStream = new NullOutputStream();

    File root = new File(cmd.getOptionValue("in"));
    File target = new File(cmd.getOptionValue("out"));
    File sinks = new File(cmd.getOptionValue("sinks"));


    if (cmd.hasOption("log")) {
        File log = new File(cmd.getOptionValue("log"));
        logStream = new FileOutputStream(log);
    }


    List<String> sinksList = new ArrayList<>();
    try (Stream<String> stream = Files.lines( Paths.get(sinks.getPath()), StandardCharsets.UTF_8))
    {
        stream.forEach(s -> {
            logger.info("Sink: " + s);
            sinksList.add(s);
        });
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }

    try (PrintStream ps = new PrintStream(logStream)) {

        List<File> files = JarUtils.listJars(root);
        for (File f: files) {
            JarUtils.unzip(f, target);
            Files.walk(Paths.get(target.getPath()))
                    .filter(Files::isRegularFile)
                    .forEach(f1 -> transform(f1, ps, sinksList));
        }


    }




}

    private static void  transform(Path p, PrintStream ps, List sinksList)  {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM7, writer) {
            String className = "";

            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                cv.visit(version, access, name, signature, superName, interfaces);
                className = name;
            }

            @java.lang.Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
                final String callerName = name;
                final String callerDesc = desc;

                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);


                return new MethodVisitor(Opcodes.ASM7, mv) {
                    HashMap<String, Integer> hm = new HashMap<>();
                    @Override
                    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {

                        String target = owner + "." + name;

                        if (sinksList.contains(target)) {
                            if (hm.containsKey(target)) {
                                hm.put(target, hm.get(target) + 1);
                            } else {
                                hm.put(target, 1);
                            }
                            String edge =  className.replace('/', '.') + "," + callerName + "," + owner.replace('/', '.')  +  "," + name + "," + hm.get(target);
                            this.visitLdcInsn(edge);
                            ps.println(edge);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "instrumenter/logger/SinkLogger", "log", "(Ljava/lang/String;)V", false);
                            //mv.visitInsn(Opcodes.POP);
                        }

                        mv.visitMethodInsn(opcode, owner, name, desc, itf);


                    }
                };
            }

        };

    if (!p.toString().endsWith("class"))
        return;
    try {
        System.err.println("Transforming " + p.toString());
        FileInputStream fis = new FileInputStream(p.toFile());
        InputStream is = new BufferedInputStream(fis);
        ClassReader reader = new ClassReader(is);
        reader.accept(visitor, 0);
        byte[] bytes = writer.toByteArray();
        new FileOutputStream(p.toFile()).write(bytes);


    } catch (Exception e) {
        e.printStackTrace();
        System.exit(-1);
    }
    }

}





