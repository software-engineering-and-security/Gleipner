package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class ArrayStoreException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public String obj;

    public ArrayStoreException_002_FP_Trampoline(SinkGadget sinkGadget, String taint, String obj) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.obj = obj;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Object arr[] = new String[3];

        try {
            arr[0] = obj;
        } catch (ArrayStoreException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
