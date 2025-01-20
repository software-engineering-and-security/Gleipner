package gleipner.chains.keywords;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class Serializable_002_FP_TrampolineMethod {

    private SinkGadget sinkGadget;
    private String taint;

    public Serializable_002_FP_TrampolineMethod(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        sinkGadget.sinkMethod(taint);
        return super.hashCode();
    }
}
