package gleipner.chains.reflection.classinitialization;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;

public class ClassInitialization_001_Trampoline extends GleipnerObject implements Serializable {

    public SinkGadget sinkGadget;
    public String taint;

    public ClassInitialization_001_Trampoline() {

    }

    public ClassInitialization_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        ClassInitialization_001_Singleton.getInstance().setSinkGadget(sinkGadget);
        ClassInitialization_001_Singleton.getInstance().setTaint(taint);

        try {
            Class.forName("gleipner.chains.reflection.classinitialization.ClassInitialization_001_LinkGadget");
        } catch (ClassNotFoundException | ExceptionInInitializerError e) {
            //throw new RuntimeException(e);
        }
        return super.hashCode();
    }

}
