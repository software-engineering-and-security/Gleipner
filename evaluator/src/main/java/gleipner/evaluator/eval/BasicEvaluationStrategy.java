package gleipner.evaluator.eval;

import gleipner.evaluator.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicEvaluationStrategy implements EvaluationStrategy{
    @Override
    public List<List<String>> read(File file) throws IOException {
        ArrayList<List<String>> chains = new ArrayList<>();
        ArrayList<String> currentChain = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        while(true) {
            String line = reader.readLine();
            if (line == null) break;

            if (line.trim().equals("")) {
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

        for (String gadget: chain) {
            Class clazz = Class.forName(gadget.split(";")[0]);
            String methodName = gadget.split(";")[1];

            if (gadget.split(";").length <= 2) {
                EvaluationStrategy.evaluateSingle(clazz.getDeclaredMethod(methodName), result);
            } else {
                String[] gadgetSplit = gadget.split(";");
                Class[] methodArgs = new Class[gadgetSplit.length - 2];
                for (int i = 2; i < gadgetSplit.length; i++) {
                    methodArgs[i - 2] = Util.signatureToType(gadgetSplit[i]);
                }
                EvaluationStrategy.evaluateSingle(clazz.getDeclaredMethod(methodName, methodArgs), result);
            }
        }

        return result;
    }
}
