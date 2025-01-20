package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstantiationException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public InstantiationException_002_FP_Trampoline() {
    }

    public InstantiationException_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        try {
            Constructor ctor = clazz.getDeclaredConstructor();
            ctor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {

        } catch (InstantiationException e) {
            sinkGadget.sinkMethod(taint);
        }


        return super.hashCode();
    }
}
