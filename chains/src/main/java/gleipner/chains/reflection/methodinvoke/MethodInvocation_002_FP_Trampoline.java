package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvocation_002_FP_Trampoline extends GleipnerObject {

    private Object sinkGadget;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] params;

    public MethodInvocation_002_FP_Trampoline(Object sinkGadget, String methodName, Class<?>[] paramTypes, String ... params) {
        this.sinkGadget = sinkGadget;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        Method m = null;
        String getterMethodName = "get" + methodName;
        try {
            m = SinkGadget.class.getDeclaredMethod(getterMethodName, paramTypes);
            m.invoke(sinkGadget, params);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            //throw new RuntimeException(e);
        }
        return super.hashCode();
    }

}
