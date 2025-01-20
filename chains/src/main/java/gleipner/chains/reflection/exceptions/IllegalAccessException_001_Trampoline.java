package gleipner.chains.reflection.exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Field;

public class IllegalAccessException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public IllegalAccessException_001_Trampoline() {
    }

    public IllegalAccessException_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        Class clazz = ExceptionHelper.class;
        ExceptionHelper helper = new ExceptionHelper();
        try {
            Field f = clazz.getDeclaredField("privateInt");
            f.set(helper, 1);
        } catch (NoSuchFieldException e) {

        } catch (IllegalAccessException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
