package gleipner.chains.reflection.constructor;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * FP - constructor is not accessible
 */
public class Constructor_004_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public Constructor_004_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        try {
            Constructor<?> ctor = Constructor_004_FP_LinkGadget_Child.class.getConstructor(String.class, SinkGadget.class);
            ctor.newInstance(this.taint, this.sinkGadget);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {

        }
        return super.hashCode();
    }
}
