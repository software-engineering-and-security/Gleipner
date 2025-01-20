package gleipner.chains.reflection.metaobjects;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.io.Serializable;
import java.lang.reflect.Array;

public class MetaObjects_019_Trampoline extends GleipnerObject {
    public Serializable objects;

    public MetaObjects_019_Trampoline() {
    }

    public MetaObjects_019_Trampoline(Serializable objects) {
        this.objects = objects;
    }

    @Override
    public int hashCode() {
        try {
            SinkGadget s = (SinkGadget) Array.get(objects, 2);
            String taint = Array.get(objects, 3).toString();
            s.sinkMethod(taint);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return super.hashCode();
    }
}
