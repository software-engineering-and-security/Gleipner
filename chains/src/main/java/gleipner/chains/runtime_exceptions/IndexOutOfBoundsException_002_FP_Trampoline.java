package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class IndexOutOfBoundsException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public static final int[] valArr = {1,2,3,4,5,6,7,8,9,10};

    public IndexOutOfBoundsException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        try {
            int x = IndexOutOfBoundsException_002_FP_Trampoline.valArr[4];
        } catch (IndexOutOfBoundsException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
