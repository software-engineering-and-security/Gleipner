package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Constructor;

public class MetaObjects_013_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_013_Trampoline() {
    }

    public MetaObjects_013_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        Constructor[] constructors = MetaImpl.class.getConstructors();
        Constructor[] declaredConstructors = MetaImpl.class.getDeclaredConstructors();

        if (constructors.length != declaredConstructors.length) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
