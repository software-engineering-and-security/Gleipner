package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Argless virtual invocation
 */
public class MethodInvocation_007_Trampoline extends GleipnerObject {

    public MethodInvocation_007_LinkGadget linkGadget;

    public MethodInvocation_007_Trampoline(MethodInvocation_007_LinkGadget linkGadget) {
        this.linkGadget = linkGadget;
    }


    @Override
    public int hashCode() {
        try {
            Method m = MethodInvocation_007_LinkGadget.class.getDeclaredMethod("invokeSink");
            m.invoke(this.linkGadget);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {

        }

        return super.hashCode();
    }
}
