package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvocation_005_Trampoline extends GleipnerObject {

    public MethodInvocation_005_LinkGadget linkGadget;
    public String taint;

    public MethodInvocation_005_Trampoline(MethodInvocation_005_LinkGadget linkGadget, String taint) {
        this.linkGadget = linkGadget;
        this.taint = taint;
    }


    @Override
    public int hashCode() {
        try {
            Method m = MethodInvocation_005_LinkGadget.class.getDeclaredMethod("invokeSink", String.class);
            m.invoke(this.linkGadget, this.taint);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {

        }

        return super.hashCode();
    }
}
