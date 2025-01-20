package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MetaObjects_007_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_007_Trampoline() {
    }

    public MetaObjects_007_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        ArrayList<Field> declaredFields = new ArrayList<>();

        for (Field f : MetaImpl.InnerMeta2.class.getDeclaredFields()) {
            if (!f.getName().contains("this")) {
                declaredFields.add(f);
            }
        }

        if (MetaImpl.InnerMeta1.class.getFields().length == declaredFields.size()) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
