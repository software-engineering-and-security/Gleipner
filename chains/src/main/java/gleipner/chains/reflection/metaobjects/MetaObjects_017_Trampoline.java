package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.AbstractMeta;
import gleipner.chains.reflection.metaobjects.util.IMeta;
import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MetaObjects_017_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public IMeta iMeta;

    public MetaObjects_017_Trampoline() {
    }

    public MetaObjects_017_Trampoline(SinkGadget sinkGadget, String taint, IMeta iMeta) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.iMeta = iMeta;
    }

    @Override
    public int hashCode() {
        try {
            Class castClass = MetaImpl.class;
            Object impl = castClass.cast(iMeta);
            Method implMethod = castClass.getDeclaredMethod("privateMethod", String.class);
            implMethod.setAccessible(true);

            String t2 = (String) implMethod.invoke(impl, taint);
            System.out.println(t2);
            sinkGadget.sinkMethod(t2);

        } catch (NoSuchMethodException | ClassCastException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }


        return super.hashCode();
    }
}
