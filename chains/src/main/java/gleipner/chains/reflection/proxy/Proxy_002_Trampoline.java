package gleipner.chains.reflection.proxy;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Proxy;

public class Proxy_002_Trampoline extends GleipnerObject {

    public String taint;
    public SinkGadget sinkGadget;


    public Proxy_002_Trampoline() {
    }

    public Proxy_002_Trampoline(String taint, SinkGadget sinkGadget) {
        this.taint = taint;
        this.sinkGadget = sinkGadget;
    }

    @Override
    public int hashCode() {

        ProxyInterface iface = (ProxyInterface) Proxy.newProxyInstance(Proxy_002_InvocationHandler.class.getClassLoader(), new Class[] {ProxyInterface.class}, new Proxy_002_InvocationHandler(this.taint));
        String val = iface.invokeSink(taint);
        sinkGadget.sinkMethod(val);

        return super.hashCode();
    }
}
