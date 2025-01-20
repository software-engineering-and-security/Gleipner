package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvocationTargetException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public int param;

    public InvocationTargetException_001_Trampoline() {
    }

    public InvocationTargetException_001_Trampoline(SinkGadget sinkGadget, String taint, int param) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.param = param;
    }

    @Override
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        ExceptionHelper helper = new ExceptionHelper();
        try {
            Method m = clazz.getMethod("doMethod", int.class);
            m.invoke(helper, param);

        } catch (NoSuchMethodException | IllegalAccessException ignored) {

        } catch (InvocationTargetException e) {
            sinkGadget.sinkMethod(taint);
        }


        return super.hashCode();
    }
}
