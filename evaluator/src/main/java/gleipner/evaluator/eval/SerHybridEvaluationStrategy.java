package gleipner.evaluator.eval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerHybridEvaluationStrategy implements EvaluationStrategy{
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
            currentChain.add(line.replace("<", "").replace(">", ""));
        }
        if (currentChain.size() > 0) {
            chains.add(currentChain);
        }

        return chains;
    }

    @Override
    public EvaluationResult evaluate(List<String> chain) throws ClassNotFoundException, NoSuchMethodException {
        return null;
    }
}
