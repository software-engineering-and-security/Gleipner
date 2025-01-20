package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Method;

public class NoSuchMethodException_001_Trampoline extends GleipnerObject {


    public SinkGadget sinkGadget;
    public String taint;

    public NoSuchMethodException_001_Trampoline() {
    }

    public NoSuchMethodException_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        try {
            Method noMethod = clazz.getMethod("doMethod", String.class);
        } catch (NoSuchMethodException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
