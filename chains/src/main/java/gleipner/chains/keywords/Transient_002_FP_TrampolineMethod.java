package gleipner.chains.keywords;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class Transient_002_FP_TrampolineMethod extends GleipnerObject {

    private transient String taint;
    private transient SinkGadget sinkGadget;

    public Transient_002_FP_TrampolineMethod(String taint, SinkGadget sinkGadget) {
        this.taint = taint;
        this.sinkGadget = sinkGadget;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        sinkGadget.sinkMethod(taint);
        return super.hashCode();
    }



}
