package gleipner.evaluator.eval;

import gleipner.core.annotations.InterProcedural;

import java.util.ArrayList;
import java.util.List;

public class EvaluationResult {
    public boolean falsePositive = false;
    public int chainLength;
    public boolean isYsoserialChain = false;
    private List<String> intraproceduralChallenges;
    private List<String> interproceduralChallenges;

    public EvaluationResult() {
        this.intraproceduralChallenges = new ArrayList<>();
        this.interproceduralChallenges = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "EvaluationResult{" +
                "falsePositive=" + falsePositive +
                ", chainLength=" + chainLength +
                ", intraproceduralChallenges=" + intraproceduralChallenges +
                ", interproceduralChallenges=" + interproceduralChallenges +
                '}';
    }

    public void addIntraProceduralChallenge(String challenge) {
        if (!this.intraproceduralChallenges.contains(challenge)) {
            this.intraproceduralChallenges.add(challenge);
        }
    }

    public void addInterProceduralChallenge(String challenge) {
        if (!this.interproceduralChallenges.contains(challenge)) {
            this.interproceduralChallenges.add(challenge);
        }
    }

    public boolean isInvokeVirtual() {
        return interproceduralChallenges.contains(InterProcedural.VIRTUAL_INVOKE);
    }

    public boolean isInvokeStatic() {
        return interproceduralChallenges.contains(InterProcedural.STATIC_INVOKE);
    }

    public boolean isDynamicProxy() {
        return interproceduralChallenges.contains(InterProcedural.PROXY);
    }

    public boolean isJNI() {
        return interproceduralChallenges.contains(InterProcedural.JNI);
    }

}
