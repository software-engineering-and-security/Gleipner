package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MetaObjects_005_Trampoline extends GleipnerObject {

    // Collections
    // Comparison of getDeclaredMethods and getMethods

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_005_Trampoline() {
    }

    public MetaObjects_005_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        ArrayList<Method> nonObjectMethods = new ArrayList<>();
        boolean isNonObjectMethod;

        for (Method m : MetaImpl.class.getMethods()) {
            isNonObjectMethod = true;

            for (Method o : Object.class.getMethods()) {
                if (o.getName().equals(m.getName())) {
                    isNonObjectMethod = false;
                    break;
                }
            }
            if (isNonObjectMethod) {
                nonObjectMethods.add(m);
            }
        }

        if (nonObjectMethods.size() == MetaImpl.class.getDeclaredMethods().length) {
            sinkGadget.sinkMethod(taint);
        }


        return super.hashCode();
    }
}
