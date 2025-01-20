package gleipner.chains.reflection.classinitialization;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.SinkAccessPaths;

public class ClassInitialization_001_Singleton {

    private static ClassInitialization_001_Singleton instance;
    private SinkGadget sinkGadget;
    private String taint;

    private ClassInitialization_001_Singleton() {

    }

    public static ClassInitialization_001_Singleton getInstance() {
        if (ClassInitialization_001_Singleton.instance == null) ClassInitialization_001_Singleton.instance = new ClassInitialization_001_Singleton();
        return ClassInitialization_001_Singleton.instance;
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
    public void invokeSinkGadget() {
        sinkGadget.sinkMethod(this.taint);
    }




}
