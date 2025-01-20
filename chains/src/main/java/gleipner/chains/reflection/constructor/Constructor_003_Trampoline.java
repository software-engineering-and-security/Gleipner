package gleipner.chains.reflection.constructor;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructor_003_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public Constructor_003_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {
        try {
            Constructor<?> ctor = Constructor_003_LinkGadget_Child.class.getConstructor(String.class, SinkGadget.class);
            ctor.newInstance(this.taint, this.sinkGadget);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {

        }
        return super.hashCode();
    }
}
