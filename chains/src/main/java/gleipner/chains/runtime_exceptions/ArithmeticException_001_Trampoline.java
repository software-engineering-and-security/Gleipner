package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class ArithmeticException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public int i;

    public ArithmeticException_001_Trampoline(SinkGadget sinkGadget, String taint, int i) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.i = i;
    }

    @Override
    public int hashCode() {

        try {
            int x = 10 / this.i;
        } catch (ArithmeticException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
