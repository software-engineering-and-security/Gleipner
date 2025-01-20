package gleipner.evaluator.eval;

import gleipner.evaluator.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerianalyzerEvaluationStrategy implements EvaluationStrategy{
    @Override
    public List<List<String>> read(File file) throws IOException {
        ArrayList<List<String>> chains = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ArrayList<String> currentChain = new ArrayList<>();

        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            if (line.trim().equals("")) {
                chains.add((List<String>) currentChain.clone());

                currentChain.clear();
                continue;
            }
            if (line.contains("Controller::invokeSink")) continue;
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
            String classComp = gadget.split("->")[0];
            String methodComp = gadget.split("->")[1];

            Class<?> clazz = Class.forName(classComp);
            String methodName = methodComp.split(" ")[0];
            String methodArgsString = methodComp.split(" ")[1];

            if (methodArgsString.contains("()")) {
                EvaluationStrategy.evaluateSingle(clazz.getDeclaredMethod(methodName), result);
            } else {
                String[] methodArgs = methodArgsString.split("\\(")[1].split("\\)")[0].split(";");
                Class<?>[] argTypes = new Class[methodArgs.length];

                for (int i = 0; i < methodArgs.length; i++) {
                    argTypes[i] = Util.signatureToType(methodArgs[i]);
                }
                EvaluationStrategy.evaluateSingle(clazz.getDeclaredMethod(methodName, argTypes), result);
            }
        }

        return result;
    }
}
