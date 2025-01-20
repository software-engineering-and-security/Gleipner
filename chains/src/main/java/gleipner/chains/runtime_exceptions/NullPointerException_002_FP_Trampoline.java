package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class NullPointerException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public static final Dummy dummy = new Dummy();

    public NullPointerException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }


    @Override
    public int hashCode() {

        try {
            int i = dummy.a;
        } catch (NullPointerException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
