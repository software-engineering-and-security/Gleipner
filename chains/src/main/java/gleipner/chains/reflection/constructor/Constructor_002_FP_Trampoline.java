package gleipner.chains.reflection.constructor;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructor_002_FP_Trampoline extends GleipnerObject {

    private Object param;
    private Class paramType;

    // in this case there is no target for a gadget chain, since there exists no constructor of interest to reach the sink gadget
    public Constructor_002_FP_Trampoline(Class paramType, Object param) {
        this.paramType = paramType;
        this.param = param;
    }


    @Override
    @FalsePositive
    public int hashCode() {
        Class<?> clazz = Constructor_002_FP_LinkGadget.class;
        try {
            Constructor<?> ctor = clazz.getDeclaredConstructor(paramType);
            ctor.newInstance(param);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            //throw new RuntimeException(e);
        }
        return super.hashCode();
    }
}
