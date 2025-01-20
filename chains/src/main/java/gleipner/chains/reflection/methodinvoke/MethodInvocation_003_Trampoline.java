package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Static Method Invocation TP
 */
public class MethodInvocation_003_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MethodInvocation_003_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {
        try {
            Method m = MethodInvocation_003_LinkGadget.class.getDeclaredMethod("invokeSink", SinkGadget.class, String.class);
            m.invoke(null, sinkGadget, taint);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }


        return super.hashCode();
    }
}
