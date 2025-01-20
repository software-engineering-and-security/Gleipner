package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TP understanding the return values of reflective method invocation
 */
public class MethodInvocation_009_Trampoline extends GleipnerObject {

    public MethodInvocation_009_ArgProvider argProvider;
    public String getTaintProp;
    public String getInvokeProp;
    public SinkGadget sinkGadget;

    public MethodInvocation_009_Trampoline(MethodInvocation_009_ArgProvider argProvider, String getTaintProp, String getInvokeProp, SinkGadget sinkGadget) {
        this.argProvider = argProvider;
        this.getTaintProp = getTaintProp;
        this.getInvokeProp = getInvokeProp;
        this.sinkGadget = sinkGadget;
    }

    @Override
    public int hashCode() {

        try {
            Method invokePropMethod = MethodInvocation_009_ArgProvider.class.getDeclaredMethod("is" + getInvokeProp);
            Method taintPropMethod = MethodInvocation_009_ArgProvider.class.getDeclaredMethod("get" + getTaintProp);

            if ((boolean) invokePropMethod.invoke(argProvider)) {
                this.sinkGadget.sinkMethod(taintPropMethod.invoke(argProvider).toString());
            }

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            ignored.printStackTrace();
        }


        return super.hashCode();
    }
}
