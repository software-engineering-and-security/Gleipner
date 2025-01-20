package gleipner.evaluator.eval;

import gleipner.evaluator.Util;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GadgetInspectorEvaluationStrategy implements EvaluationStrategy{
    @Override
    public List<List<String>> read(File file) throws IOException {

        ArrayList<List<String>> chains = new ArrayList<>();
        ArrayList<String> currentChain = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            if (!line.startsWith(" ")) {
                if (currentChain.size() > 0) chains.add((List<String>) currentChain.clone());
                currentChain.clear();
            }
            String gadget = line.trim().split(" ")[0];
            currentChain.add(gadget);
        }
        if (currentChain.size() > 0) {
            chains.add(currentChain);
        }
        return chains;
    }

    @Override
    public EvaluationResult evaluate(List<String> chain) throws ClassNotFoundException, NoSuchMethodException {

        EvaluationResult result = new EvaluationResult();

        for (String gadget: chain) {
            if (gadget.trim().equals("")) continue;
            String classSignature = gadget.split("\\.")[0].replaceAll("/", ".");
            Class clazz = Class.forName(classSignature);

            String methodSignatureString = gadget.split("\\.")[1];
            String methodName = methodSignatureString.split("\\(")[0];

            Method method;

            if (methodSignatureString.contains("()")) {
                method = clazz.getDeclaredMethod(methodName);
            } else {
                String methodArgsString = methodSignatureString.split("\\(")[1].split("\\)")[0];
                String[] methodArgs = methodArgsString.split(";");

                Class<?>[] methodArgTypes = new Class[methodArgs.length];

                for (int i = 0; i < methodArgs.length; i++) {
                    methodArgTypes[i] = Util.signatureToType(methodArgs[i]);
                }
                method = clazz.getDeclaredMethod(methodName, methodArgTypes);
            }

            EvaluationStrategy.evaluateSingle(method, result);
        }

        return result;
    }



}
