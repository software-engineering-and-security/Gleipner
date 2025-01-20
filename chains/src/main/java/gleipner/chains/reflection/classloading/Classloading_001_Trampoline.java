package gleipner.chains.reflection.classloading;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class Classloading_001_Trampoline extends GleipnerObject {

    private static final ClassLoader classLoader = new Classloading_ClassLoader();
    public SinkGadget sinkGadget;
    public String taint;


    public Classloading_001_Trampoline() {
    }

    public Classloading_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {
        try {
            Class clazz = classLoader.loadClass("src.main.java.LoadedClass");
            boolean isFlagTrue = clazz.getField("FLAG").getBoolean(null);
            if (isFlagTrue) {
                sinkGadget.sinkMethod(taint);
            }
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return super.hashCode();
    }
}
