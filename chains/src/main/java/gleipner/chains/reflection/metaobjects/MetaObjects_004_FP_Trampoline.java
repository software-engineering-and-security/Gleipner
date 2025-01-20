package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class MetaObjects_004_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_004_FP_Trampoline() {
    }

    public MetaObjects_004_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        Class[] ifaces = MetaImpl.class.getInterfaces();

        if (ifaces.length == 1) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
