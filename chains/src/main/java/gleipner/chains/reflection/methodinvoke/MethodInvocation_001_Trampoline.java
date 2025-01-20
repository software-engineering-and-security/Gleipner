package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvocation_001_Trampoline extends GleipnerObject {

    private Object sinkGadget;
    private String methodName;
    private Class<?>[] paramTypes;
    private String[] params;

    public MethodInvocation_001_Trampoline(Object sinkGadget, String methodName, Class<?>[] paramTypes, String ... params) {
        this.sinkGadget = sinkGadget;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    @Override
    public int hashCode() {
        Method m = null;
        try {
            m = SinkGadget.class.getDeclaredMethod(methodName, paramTypes);
            m.invoke(sinkGadget, params);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return super.hashCode();
    }

}
