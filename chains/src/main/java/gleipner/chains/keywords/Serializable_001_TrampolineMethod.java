package gleipner.chains.keywords;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class Serializable_001_TrampolineMethod extends GleipnerObject {

    private SinkGadget sinkGadget;
    private String taint;

    public Serializable_001_TrampolineMethod(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {
        sinkGadget.sinkMethod(taint);
        return super.hashCode();
    }
}
