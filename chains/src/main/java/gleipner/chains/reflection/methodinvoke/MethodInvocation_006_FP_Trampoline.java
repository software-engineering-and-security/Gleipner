package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Virtual Method Invocation False Positive
 * fails because taint flow of boolean prevents execution in next method
 */
public class MethodInvocation_006_FP_Trampoline extends GleipnerObject {

    public MethodInvocation_006_FP_LinkGadget linkGadget;
    public String taint;

    public MethodInvocation_006_FP_Trampoline(MethodInvocation_006_FP_LinkGadget linkGadget, String taint) {
        this.linkGadget = linkGadget;
        this.taint = taint;
    }


    @Override
    @FalsePositive
    public int hashCode() {
        try {
            Method m = MethodInvocation_006_FP_LinkGadget.class.getDeclaredMethod("invokeSink", String.class, boolean.class);
            m.invoke(this.linkGadget, this.taint, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {

        }

        return super.hashCode();
    }
}
