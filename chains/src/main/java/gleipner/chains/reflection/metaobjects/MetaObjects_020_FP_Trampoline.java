package gleipner.chains.reflection.metaobjects;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.io.Serializable;
import java.lang.reflect.Array;

public class MetaObjects_020_FP_Trampoline extends GleipnerObject {
    public Serializable objects;

    public MetaObjects_020_FP_Trampoline() {
    }

    public MetaObjects_020_FP_Trampoline(Serializable objects) {
        this.objects = objects;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        try {
            Array.set(objects, 0, "foo");

            SinkGadget sinkGadget = (SinkGadget) Array.get(objects, 0);
            sinkGadget.sinkMethod(Array.get(objects, 1).toString());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return super.hashCode();
    }
}
