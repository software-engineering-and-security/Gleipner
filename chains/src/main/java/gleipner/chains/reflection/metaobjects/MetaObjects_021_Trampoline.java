package gleipner.chains.reflection.metaobjects;

import gleipner.core.GleipnerObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MetaObjects_021_Trampoline extends GleipnerObject {

    public Serializable objects;

    public MetaObjects_021_Trampoline() {
    }

    public MetaObjects_021_Trampoline(Serializable objects) {
        this.objects = objects;
    }

    @Override
    public int hashCode() {

        Object targetObjects = Array.newInstance(Serializable.class, 3);

        if (Array.getLength(targetObjects) == Array.getLength(objects)) {
            Array.set(targetObjects, 0, Array.get(objects, 0));
            Array.set(targetObjects, 1, Array.get(objects, 1));
            Array.set(targetObjects, 2, Array.get(objects, 2));
        }

        try {
            Method m = Array.get(targetObjects, 1).getClass()
                    .getMethod(Array.get(targetObjects, 0).toString(), String.class);
            m.invoke(Array.get(targetObjects, 1), Array.get(targetObjects, 2));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return super.hashCode();
    }
}
