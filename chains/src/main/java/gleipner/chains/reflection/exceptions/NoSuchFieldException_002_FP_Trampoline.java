package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Field;

public class NoSuchFieldException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public NoSuchFieldException_002_FP_Trampoline() {
    }

    public NoSuchFieldException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        try {
            Field f = clazz.getDeclaredField("publicInt");
        } catch (NoSuchFieldException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
