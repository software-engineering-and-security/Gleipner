package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class IndexOutOfBoundsException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public int index;
    public static final int[] valArr = {1,2,3,4,5,6,7,8,9,10};

    public IndexOutOfBoundsException_001_Trampoline(SinkGadget sinkGadget, String taint, int index) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.index = index;
    }

    @Override
    public int hashCode() {

        try {
            int x = IndexOutOfBoundsException_001_Trampoline.valArr[index];
        } catch (IndexOutOfBoundsException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
