package gleipner.chains.reflection.metaobjects;

import gleipner.chains.reflection.metaobjects.util.MetaAnnotation;
import gleipner.chains.reflection.metaobjects.util.MetaAnnotation2;
import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.annotation.Annotation;

public class MetaObjects_009_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public MetaObjects_009_Trampoline() {
    }

    public MetaObjects_009_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        Annotation[] annotations1 = MetaImpl.class.getAnnotations();
        Annotation[] annotations2 = MetaImpl.InnerMeta1.class.getAnnotations();
        Annotation[] annotations3 = MetaImpl.InnerMeta2.class.getAnnotations();

        if (annotations1.length == annotations2.length && annotations2.length == annotations3.length) {
            for (Annotation a : annotations1) {
                for (Annotation aa : annotations2) {
                    if (a instanceof MetaAnnotation && aa instanceof MetaAnnotation) {
                        if (((MetaAnnotation) a).value() == ((MetaAnnotation) aa).value()) {
                            sinkGadget.sinkMethod(taint);
                        }
                    }
                    else if (a instanceof MetaAnnotation2 && aa instanceof MetaAnnotation2) {
                        if (((MetaAnnotation2) a).value() == ((MetaAnnotation2) aa).value()) {
                            sinkGadget.sinkMethod(taint);
                        }
                    }
                }
            }
        }

        return super.hashCode();
    }
}
