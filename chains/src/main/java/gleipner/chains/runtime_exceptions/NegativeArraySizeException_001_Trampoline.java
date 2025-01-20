package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class NegativeArraySizeException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public int size;

    public NegativeArraySizeException_001_Trampoline(SinkGadget sinkGadget, String taint, int size) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.size = size;
    }

    @Override
    public int hashCode() {

        int[] arr;

        try {
            arr = new int[this.size];
        } catch (NegativeArraySizeException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
