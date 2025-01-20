package gleipner.chains.reflection.constructor;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructor_006_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public Constructor_006_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        try {
            Constructor<?> ctor = Constructor_006_FP_ArgProvider.class.getConstructor(boolean.class);
            Constructor_006_FP_ArgProvider obj = (Constructor_006_FP_ArgProvider) ctor.newInstance(false);

            if (obj.b)
                sinkGadget.sinkMethod(taint);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {

        }

        return super.hashCode();
    }
}
