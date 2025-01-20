package gleipner.chains.reflection.proxy;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy_001_InvocationHandler implements InvocationHandler, Serializable {

    public SinkGadget sink;

    public Proxy_001_InvocationHandler() {
    }

    public Proxy_001_InvocationHandler(SinkGadget sink) {
        this.sink = sink;
    }

    @Override
    @InterProcedural(InterProcedural.PROXY)
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("invokeSink")) {
            sink.sinkMethod((String) args[0]);
        }

        return null;
    }
}
