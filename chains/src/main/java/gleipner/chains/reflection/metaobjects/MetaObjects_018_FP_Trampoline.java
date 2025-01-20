package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.AbstractMeta;
import gleipner.chains.reflection.metaobjects.util.IMeta;
import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MetaObjects_018_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public IMeta iMeta;

    public MetaObjects_018_FP_Trampoline() {
    }

    public MetaObjects_018_FP_Trampoline(SinkGadget sinkGadget, String taint, IMeta iMeta) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.iMeta = iMeta;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        try {
            Class superClass = MetaImpl.class.getSuperclass();

            Object impl = superClass.cast(iMeta);
            Method implMethod = superClass.getDeclaredMethod("privateMethod", String.class);
            implMethod.setAccessible(true);

            String t2 = (String) implMethod.invoke(impl, taint);
            sinkGadget.sinkMethod(t2);

        } catch (NoSuchMethodException | ClassCastException | InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }


        return super.hashCode();
    }
}
