package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class NullPointerException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public Dummy dummy;

    public NullPointerException_001_Trampoline(SinkGadget sinkGadget, String taint, Dummy dummy) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.dummy = dummy;
    }

    @Override
    public int hashCode() {

        try {
            int i =  dummy.a;
        } catch (NullPointerException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
