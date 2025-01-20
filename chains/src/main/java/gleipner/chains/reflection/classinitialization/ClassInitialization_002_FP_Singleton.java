package gleipner.chains.reflection.classinitialization;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.SinkAccessPaths;

public class ClassInitialization_002_FP_Singleton {

    private static ClassInitialization_002_FP_Singleton instance;
    private SinkGadget sinkGadget;
    private String taint;

    private ClassInitialization_002_FP_Singleton() {

    }

    public static ClassInitialization_002_FP_Singleton getInstance() {
        if (ClassInitialization_002_FP_Singleton.instance == null) ClassInitialization_002_FP_Singleton.instance = new ClassInitialization_002_FP_Singleton();
        return ClassInitialization_002_FP_Singleton.instance;
    }

    public void setSinkGadget(SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    public void setTaint(String taint) {
        this.taint = taint;
    }

    @InterProcedural(InterProcedural.CLINIT)
    @ChainLength(3)
    @SinkAccessPaths(1)
    public void invokeSinkGadget(boolean doInvoke) {
        if (doInvoke)
            sinkGadget.sinkMethod(this.taint);
    }




}
