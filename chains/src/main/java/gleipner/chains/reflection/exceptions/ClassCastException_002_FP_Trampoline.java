package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class ClassCastException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public ClassCastException_002_FP_Trampoline() {
    }

    public ClassCastException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        ExceptionHelper helper = new ExceptionHelper();
        Class clazz = ParentExceptionHelper.class;
        try {
            clazz.cast(helper);
        } catch (ClassCastException e) {
            sinkGadget.sinkMethod(taint);
        }
        return super.hashCode();
    }
}
