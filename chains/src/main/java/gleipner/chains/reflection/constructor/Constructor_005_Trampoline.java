package gleipner.chains.reflection.constructor;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructor_005_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;

    public Constructor_005_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        try {
            Constructor<?> ctor = Constructor_005_ArgProvider.class.getConstructor(boolean.class);
            Constructor_005_ArgProvider obj = (Constructor_005_ArgProvider) ctor.newInstance(true);

            if (obj.b)
                sinkGadget.sinkMethod(taint);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {

        }

        return super.hashCode();
    }
}
