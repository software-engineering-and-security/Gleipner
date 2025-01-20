package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class NegativeArraySizeException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public int size;

    public NegativeArraySizeException_002_FP_Trampoline(SinkGadget sinkGadget, String taint, int size) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.size = size;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        int[] arr;
        if (size < 0) this.size *= -1;

        try {
            arr = new int[this.size];
        } catch (NegativeArraySizeException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
