package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class ClassCastException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public ClassCastException_001_Trampoline() {
    }

    public ClassCastException_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        ParentExceptionHelper helper = new ParentExceptionHelper();
        Class clazz = ExceptionHelper.class;
        try {
            clazz.cast(helper);
        } catch (ClassCastException e) {
            sinkGadget.sinkMethod(taint);
        }
        return super.hashCode();
    }
}
