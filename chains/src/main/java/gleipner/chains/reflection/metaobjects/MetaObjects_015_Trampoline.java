package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Field;

public class MetaObjects_015_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_015_Trampoline() {
    }

    public MetaObjects_015_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {
        try {
            Field privateField = MetaImpl.class.getDeclaredField("privateField");
            if (privateField.isAccessible()) {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        sinkGadget.sinkMethod(taint);

        return super.hashCode();
    }
}
