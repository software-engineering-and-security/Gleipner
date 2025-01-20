package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class MetaObjects_001_Trampoline extends GleipnerObject {

    //GetClasses
    //GetFields

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_001_Trampoline() {
    }

    public MetaObjects_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        Class[] clazzes = MetaImpl.class.getClasses();
        int propCnt = 0;

        for (Class clazz: clazzes) {
            propCnt += clazz.getDeclaredFields().length;
        }
        if (propCnt > 5) {
            sinkGadget.sinkMethod(taint);
        }


        return super.hashCode();
    }
}
