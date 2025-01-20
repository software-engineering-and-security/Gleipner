package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstantiationException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public InstantiationException_001_Trampoline() {
    }

    public InstantiationException_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        Class clazz = AbstractExceptionHelper.class;
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
