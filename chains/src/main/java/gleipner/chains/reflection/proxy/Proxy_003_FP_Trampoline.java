package gleipner.chains.reflection.proxy;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Proxy;

public class Proxy_003_FP_Trampoline extends GleipnerObject {

    public String taint;
    public SinkGadget sinkGadget;


    public Proxy_003_FP_Trampoline() {
    }

    public Proxy_003_FP_Trampoline(String taint, SinkGadget sinkGadget) {
        this.taint = taint;
        this.sinkGadget = sinkGadget;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        ProxyInterface iface = (ProxyInterface) Proxy.newProxyInstance(Proxy_003_FP_InvocationHandler.class.getClassLoader(), new Class[] {ProxyInterface.class}, new Proxy_003_FP_InvocationHandler(this.taint));
        String val = iface.invokeSink(taint);
        sinkGadget.sinkMethod(val);

        return super.hashCode();
    }
}
