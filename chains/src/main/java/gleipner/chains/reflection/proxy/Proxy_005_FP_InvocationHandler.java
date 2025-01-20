package gleipner.chains.reflection.proxy;

import gleipner.core.SinkGadget;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy_005_FP_InvocationHandler implements InvocationHandler, Serializable {

    public Proxy_005_FP_Interface2 iface;
    public SinkGadget sinkGadget;

    public Proxy_005_FP_InvocationHandler() {

    }

    public Proxy_005_FP_InvocationHandler(Proxy_005_FP_Interface2 iface, SinkGadget sinkGadget) {
        this.iface = iface;
        this.sinkGadget = sinkGadget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("doMethod"))
            iface.invokeSink("notTainted");
        else if (method.getName().equals("invokeSink"))
            sinkGadget.sinkMethod(args[0].toString());

        return null;
    }
}
