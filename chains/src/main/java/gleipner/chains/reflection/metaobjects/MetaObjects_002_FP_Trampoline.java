package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class MetaObjects_002_FP_Trampoline extends GleipnerObject {

    //GetClasses
    //GetFields

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_002_FP_Trampoline() {
    }

    public MetaObjects_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Class[] clazzes = MetaImpl.class.getClasses();
        int propCnt = 0;

        for (Class clazz: clazzes) {
            propCnt += clazz.getDeclaredFields().length;
        }
        if (propCnt < 5) {
            sinkGadget.sinkMethod(taint);
        }


        return super.hashCode();
    }
}
