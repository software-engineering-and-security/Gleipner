package gleipner.chains.reflection.classinitialization;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;
import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;

public class ClassInitialization_002_FP_Trampoline extends GleipnerObject implements Serializable {

    public SinkGadget sinkGadget;
    public String taint;

    public ClassInitialization_002_FP_Trampoline() {

    }

    public ClassInitialization_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @FalsePositive
    public int hashCode() {
        ClassInitialization_002_FP_Singleton.getInstance().setSinkGadget(sinkGadget);
        ClassInitialization_002_FP_Singleton.getInstance().setTaint(taint);

        try {
            Class.forName("gleipner.chains.reflection.classinitialization.ClassInitialization_002_FP_LinkGadget");
        } catch (ClassNotFoundException | ExceptionInInitializerError e) {
            //throw new RuntimeException(e);
        }
        return super.hashCode();
    }

}
