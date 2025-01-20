package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class ArithmeticException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public static final int i = 10;

    public ArithmeticException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        try {
            int x = 10 / ArithmeticException_002_FP_Trampoline.i;
        } catch (ArithmeticException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
