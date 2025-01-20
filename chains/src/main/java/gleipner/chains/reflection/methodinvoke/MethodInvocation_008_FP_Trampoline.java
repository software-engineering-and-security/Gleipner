package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Argless virtual invocation FP
 * invocation fails because linkGadget method is inaccessible
 */
public class MethodInvocation_008_FP_Trampoline extends GleipnerObject {

    public MethodInvocation_008_FP_LinkGadget linkGadget;

    public MethodInvocation_008_FP_Trampoline(MethodInvocation_008_FP_LinkGadget linkGadget) {
        this.linkGadget = linkGadget;
    }


    @Override
    @FalsePositive
    public int hashCode() {
        try {
            Method m = MethodInvocation_008_FP_LinkGadget.class.getDeclaredMethod("invokeSink");
            m.invoke(this.linkGadget);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {

        }

        return super.hashCode();
    }
}
