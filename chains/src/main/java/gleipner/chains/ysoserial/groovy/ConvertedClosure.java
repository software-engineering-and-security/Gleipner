package gleipner.chains.ysoserial.groovy;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;
import java.lang.reflect.Method;

public class ConvertedClosure extends ConversionHandler implements Serializable {
    private String methodName;

    public ConvertedClosure(Closure closure, String method, SinkGadget sinkGadget) {
        super(closure);
        this.methodName = method;
        this.sinkGadget = sinkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public Object invokeCustom(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodName !=null && !methodName.equals(method.getName())) return null;
        return ((Closure) getDelegate()).call(args);
    }
}
