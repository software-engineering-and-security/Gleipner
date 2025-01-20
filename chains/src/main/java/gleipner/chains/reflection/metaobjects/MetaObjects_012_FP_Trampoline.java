package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.IMeta;
import gleipner.chains.reflection.metaobjects.util.MetaInvocationHandler;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Proxy;

public class MetaObjects_012_FP_Trampoline extends GleipnerObject {

    public String taint;
    public SinkGadget sinkGadget;

    public MetaObjects_012_FP_Trampoline() {
    }

    public MetaObjects_012_FP_Trampoline(String taint, SinkGadget sinkGadget) {
        this.taint = taint;
        this.sinkGadget = sinkGadget;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Object o = Proxy.newProxyInstance(IMeta.class.getClassLoader(), new Class[] {IMeta.class}, new MetaInvocationHandler());
        if (!Proxy.isProxyClass(o.getClass())) {
            sinkGadget.sinkMethod(taint);
        }
        return super.hashCode();
    }
}
