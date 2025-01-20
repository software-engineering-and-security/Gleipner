package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Method;

public class NoSuchMethodException_002_FP_Trampoline extends GleipnerObject {


    public SinkGadget sinkGadget;
    public String taint;

    public NoSuchMethodException_002_FP_Trampoline() {
    }

    public NoSuchMethodException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        try {
            Method noMethod = clazz.getMethod("doMethod", int.class);
        } catch (NoSuchMethodException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
