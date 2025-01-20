package gleipner.chains.reflection.classloading;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

public class Classloading_002_FP_Trampoline extends GleipnerObject {

    private static final ClassLoader classLoader = new Classloading_ClassLoader();
    public SinkGadget sinkGadget;
    public String taint;


    public Classloading_002_FP_Trampoline() {

    }

    public Classloading_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        try {
            Class clazz = classLoader.loadClass("src.main.java.LoadedClass");
            boolean isFlagTrue = clazz.getField("FLAG").getBoolean(null);
            if (isFlagTrue == false) {
                sinkGadget.sinkMethod(taint);
            }
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return super.hashCode();
    }
}
