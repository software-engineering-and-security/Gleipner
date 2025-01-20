package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Field;

public class IllegalAccessException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public IllegalAccessException_002_FP_Trampoline() {
    }

    public IllegalAccessException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        ExceptionHelper helper = new ExceptionHelper();
        try {
            Field f = clazz.getDeclaredField("publicInt");
            f.set(helper, 1);
        } catch (NoSuchFieldException e) {

        } catch (IllegalAccessException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
