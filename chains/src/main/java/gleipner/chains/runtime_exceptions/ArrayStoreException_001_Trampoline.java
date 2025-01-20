package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class ArrayStoreException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public Object obj;

    public ArrayStoreException_001_Trampoline(SinkGadget sinkGadget, String taint, Object obj) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.obj = obj;
    }

    @Override
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
