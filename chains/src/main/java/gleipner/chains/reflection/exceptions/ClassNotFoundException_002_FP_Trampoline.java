package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class ClassNotFoundException_002_FP_Trampoline extends GleipnerObject {

    private static final String EXISTING_CLASS = "gleipner.chains.reflection.exceptions.ClassNotFoundException_002_FP_Trampoline";
    public SinkGadget sinkGadget;
    public String taint;

    public ClassNotFoundException_002_FP_Trampoline() {
    }

    public ClassNotFoundException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        try {
            Class.forName(EXISTING_CLASS);
        } catch (ClassNotFoundException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
