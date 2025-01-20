package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvocationTargetException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public InvocationTargetException_002_FP_Trampoline() {
    }

    public InvocationTargetException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        ExceptionHelper helper = new ExceptionHelper();
        try {
            Method m = clazz.getMethod("doMethod", int.class);
            m.invoke(helper, 1);
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            
        } catch (InvocationTargetException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
