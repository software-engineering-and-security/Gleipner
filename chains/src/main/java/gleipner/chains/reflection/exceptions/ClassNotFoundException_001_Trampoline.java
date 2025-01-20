package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class ClassNotFoundException_001_Trampoline extends GleipnerObject {

    private static final String NOT_EXISTING_CLASS = "not.existing.Clazz";
    public SinkGadget sinkGadget;
    public String taint;

    public ClassNotFoundException_001_Trampoline() {
    }

    public ClassNotFoundException_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        try {
            Class.forName(NOT_EXISTING_CLASS);
        } catch (ClassNotFoundException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
