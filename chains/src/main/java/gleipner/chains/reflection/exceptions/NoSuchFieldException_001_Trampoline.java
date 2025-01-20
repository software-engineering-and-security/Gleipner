package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Field;

public class NoSuchFieldException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public NoSuchFieldException_001_Trampoline() {
    }

    public NoSuchFieldException_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        try {
            Field f = clazz.getDeclaredField("publicString");
        } catch (NoSuchFieldException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
