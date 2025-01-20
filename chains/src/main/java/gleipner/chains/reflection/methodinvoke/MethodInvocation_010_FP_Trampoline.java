package gleipner.chains.reflection.methodinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * FP understanding the return values of reflective method invocation
 */
public class MethodInvocation_010_FP_Trampoline extends GleipnerObject {

    public MethodInvocation_010_FP_ArgProvider argProvider;
    public String getTaintProp;
    public String getInvokeProp;
    public SinkGadget sinkGadget;

    public MethodInvocation_010_FP_Trampoline(MethodInvocation_010_FP_ArgProvider argProvider, String getTaintProp, String getInvokeProp, SinkGadget sinkGadget) {
        this.argProvider = argProvider;
        this.getTaintProp = getTaintProp;
        this.getInvokeProp = getInvokeProp;
        this.sinkGadget = sinkGadget;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        try {
            Method invokePropMethod = MethodInvocation_010_FP_ArgProvider.class.getDeclaredMethod("is" + getInvokeProp);
            Method taintPropMethod = MethodInvocation_010_FP_ArgProvider.class.getDeclaredMethod("get" + getTaintProp);

            if ((boolean) invokePropMethod.invoke(argProvider)) {
                this.sinkGadget.sinkMethod(taintPropMethod.invoke(argProvider).toString());
            }

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
        }


        return super.hashCode();
    }
}
