package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.AbstractMeta;
import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class MetaObjects_003_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_003_Trampoline() {
    }

    public MetaObjects_003_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {
        Class[] ifaces = AbstractMeta.class.getInterfaces();

        if (ifaces.length == 1) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
