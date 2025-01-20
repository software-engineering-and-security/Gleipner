package gleipner.chains.reflection.proxy;

import gleipner.core.SinkGadget;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy_004_InvocationHandler implements InvocationHandler, Serializable {

    public Proxy_004_Interface2 iface;
    public SinkGadget sinkGadget;

    public Proxy_004_InvocationHandler() {

    }

    public Proxy_004_InvocationHandler(Proxy_004_Interface2 iface, SinkGadget sinkGadget) {
        this.iface = iface;
        this.sinkGadget = sinkGadget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("doMethod"))
            iface.invokeSink(args[0].toString());
        else if (method.getName().equals("invokeSink"))
            sinkGadget.sinkMethod(args[0].toString());

        return null;
    }
}
