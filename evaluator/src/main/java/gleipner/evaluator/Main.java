package gleipner.evaluator;

import gleipner.evaluator.eval.EvaluationResult;
import gleipner.evaluator.eval.EvaluationStrategy;
import gleipner.evaluator.eval.EvaluationStrategyFactory;

import java.io.*;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static int TRUE_POSITIVE_COUNT = 0;
    public static int FALSE_POSITIVE_COUNT = 0;

    public static void readManifest(String gleipner_jar) throws IOException {
        JarInputStream jarStream = new JarInputStream(new FileInputStream(gleipner_jar));
        Manifest mf = jarStream.getManifest();

        Attributes attr = mf.getMainAttributes();
        if (attr.getValue("chains-tp") == null) {
            System.out.println("[WARN] " + gleipner_jar + " contains no attribute chains-tp in manifest file, results have no indication of the amount of true positives in the chain");
        } else {
            TRUE_POSITIVE_COUNT = Integer.parseInt(attr.getValue("chains-tp"));
        }
        if (attr.getValue("chains-fp") == null) {
            System.out.println("[WARN] " + gleipner_jar + " contains no attribute chains-fp in manifest file, results have no indication of the amount of false positives in the chain");
        } else {
            FALSE_POSITIVE_COUNT = Integer.parseInt(attr.getValue("chains-fp"));
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IOException {

        if (args.length < 2) {
            System.out.println("Usage: java -jar evaluator.jar [chainsFile] [gleipner_jar] <format> <outputFile>]");
            System.out.println("<format>: basic (default), gadgetinspector, serianalyzer");
            System.exit(0);
        }

        String filePath = args[0];
        EvaluationStrategy eval = EvaluationStrategyFactory.getInstance();

        Main.readManifest(args[1]);

        if (args.length >= 3) {
            eval = EvaluationStrategyFactory.getInstance(args[2]);
            if (eval == null) {
                System.out.println("Unknown format " + args[2]);
                System.exit(1);
            }
        }

        List<List<String>> chains = eval.read(new File(filePath));

        int falsePositiveCnt  = 0;
        int ysoserial_cnt = 0;

        for (List<String> chain : chains) {
            EvaluationResult result = eval.evaluate(chain);
            if (result.falsePositive) {
                falsePositiveCnt++;
                continue;
            }

            if (result.isYsoserialChain) ysoserial_cnt++;
        }

        PrintStream output = System.out;
        // if file passed: write output, else print results to screen
        if (args.length >= 4) {
            output = new PrintStream(args[3]);
        }

        String falsePositiveString = falsePositiveCnt > 0 ? RED + falsePositiveCnt + RESET : GREEN + falsePositiveCnt + RESET;
        int truePositives = chains.size() - falsePositiveCnt;
        String truePositiveString = truePositives == TRUE_POSITIVE_COUNT ? GREEN + truePositives + RESET : RED + truePositives + RESET;
        output.println("======Evaluation Results=======");
        output.println("File: " + args[0] + "\n");
        output.println(String.format("True positives: %s/%s", truePositiveString, TRUE_POSITIVE_COUNT));
        output.println(String.format("False positives: %s/%s", falsePositiveString, FALSE_POSITIVE_COUNT));
        output.println(String.format("Ysoserial Chains: %s", ysoserial_cnt));



    }


}