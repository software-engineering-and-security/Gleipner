package gleipner.chains.reflection.constructor;

import gleipner.core.GleipnerObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructor_001_Trampoline extends GleipnerObject {

    private Object[] params;
    private Class[] paramTypes;


    public Constructor_001_Trampoline(Class[] paramTypes, Object ... params) {
        this.paramTypes = paramTypes;
        this.params = params;
    }

    @Override
    public int hashCode() {
        Class<?> clazz = Constructor_001_LinkGadget.class;
        try {
            Constructor<?> ctor = clazz.getDeclaredConstructor(paramTypes);
            ctor.newInstance(params);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException ignored) {
        }
        return super.hashCode();
    }
}
