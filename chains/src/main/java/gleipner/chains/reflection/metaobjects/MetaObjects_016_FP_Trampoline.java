package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Field;

public class MetaObjects_016_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_016_FP_Trampoline() {
    }

    public MetaObjects_016_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        try {
            Field privateField = MetaImpl.class.getDeclaredField("privateField");
            if (privateField.isAccessible()) {
                sinkGadget.sinkMethod(taint);
            }
        } catch (Exception e) {
            return 0;
        }
        return super.hashCode();
    }

}
