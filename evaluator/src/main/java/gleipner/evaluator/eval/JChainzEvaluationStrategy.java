package gleipner.evaluator.eval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JChainzEvaluationStrategy implements EvaluationStrategy{
    @Override
    public List<List<String>> read(File file) throws IOException {
        ArrayList<List<String>> chains = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ArrayList<String> currentChain = new ArrayList<>();

        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            if (line.trim().equals("") || line.trim().equals("[")) continue;
            if (line.trim().equals("]")) {
                chains.add((List<String>) currentChain.clone());
                currentChain.clear();
                continue;
            }

            currentChain.add(line);
        }
        if (currentChain.size() > 0) {
            chains.add(currentChain);
        }

        return chains;
    }

    @Override
    public EvaluationResult evaluate(List<String> chain) throws ClassNotFoundException, NoSuchMethodException {

        EvaluationResult result = new EvaluationResult();

        for (String gadget : chain) {
            gadget = gadget.replace(">", "").replace("<", "").trim();

            String classComp = gadget.split(":")[0];
            String methodComp = gadget.split(":")[1].trim().split(" ")[1];

            Class<?> clazz = Class.forName(classComp);
            String methodName = methodComp.split("\\(")[0];
            String methodArgsString = methodComp.split("\\(")[1].replace(")", "").trim();

            if (methodArgsString.equals("")) {
                EvaluationStrategy.evaluateSingle(clazz.getDeclaredMethod(methodName), result);
            } else {
                String[] methodArgs = methodArgsString.split(",");
                if (methodArgs.length == 0) methodArgs = new String[] {methodArgsString};

                Class<?>[] argTypes = new Class[methodArgs.length];
                for (int i = 0; i < methodArgs.length; i++) {
                    // catch array types
                    if (methodArgs[i].contains("[]")) {
                        methodArgs[i] = methodArgs[i].replace("[", "");
                        methodArgs[i] = methodArgs[i].replace("]", "");
                        Class c = Class.forName(methodArgs[i]);
                        argTypes[i] = Array.newInstance(c, 0).getClass();
                    } else {
                        argTypes[i] = Class.forName(methodArgs[i]);
                    }

                }

                EvaluationStrategy.evaluateSingle(clazz.getDeclaredMethod(methodName, argTypes), result);
            }
        }
        return result;

    }
}
