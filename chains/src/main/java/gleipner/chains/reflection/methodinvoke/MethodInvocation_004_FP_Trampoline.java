package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Static Method Invocation FP
 * Invocation will fail because invokeSink is an instanceMethod in the linkGadget
 */
public class MethodInvocation_004_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MethodInvocation_004_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        try {
            Method m = MethodInvocation_004_FP_LinkGadget.class.getDeclaredMethod("invokeSink", SinkGadget.class, String.class);
            m.invoke(null, sinkGadget, taint);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NullPointerException ignored) {

        }


        return super.hashCode();
    }



}
