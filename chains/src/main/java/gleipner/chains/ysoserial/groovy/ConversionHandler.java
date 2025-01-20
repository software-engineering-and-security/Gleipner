package gleipner.chains.ysoserial.groovy;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class ConversionHandler implements InvocationHandler, Serializable {

    private Object delegate;
    SinkGadget sinkGadget;

    public ConversionHandler(Object delegate) {
        if (delegate == null) throw new IllegalArgumentException("delegate must not be null");
        this.delegate = delegate;
    }

    @Override
    @InterProcedural(InterProcedural.PROXY)
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Workaround for the chain to be like the real chain, but still use the sinkGadget as the final gadget
        ProcessGroovyMethods.sinkGadget = this.sinkGadget;

        if (!checkMethod(method)) {
            return invokeCustom(proxy, method, args);
        }
        return method.invoke(this,args);
    }

    public Object getDelegate() {
        return delegate;
    }

    public abstract Object invokeCustom(Object proxy, Method method, Object[] args) throws Throwable;

    protected boolean checkMethod(Method method) {
        return isCoreObjectMethod(method);
    }

    public static boolean isCoreObjectMethod(Method method) {
        return Object.class.equals(method.getDeclaringClass());
    }
}
