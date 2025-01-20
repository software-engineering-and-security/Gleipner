package gleipner.chains.reflection.classloading;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Classloading_003_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public static final Classloading_ClassLoader_003 classLoader = new Classloading_ClassLoader_003();

    public Classloading_003_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        try {
            Class clz = Classloading_003_Trampoline.classLoader.findClass("gleipner.chains.reflection.classloading.LoadedClass_003");
            Method m = clz.getMethod("invokeSink", SinkGadget.class, String.class);
            m.invoke(null, this.sinkGadget, this.taint);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("Probably the LoadedClass_003.class file is missing in the resources directory: " + e.getMessage());
        }


        return super.hashCode();
    }
}
