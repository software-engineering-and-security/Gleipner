package gleipner.chains.ysoserial.groovy;

import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Closure<V> extends GroovyObjectSupport implements Serializable {

    private Object delegate;
    private Object owner;
    private Object thisObject;

    private static final Object[] EMPTY_OBJECT_ARRAY = {};

    public Closure(Object owner, Object thisObject) {
        this.owner = owner;
        this.delegate = owner;
        this.thisObject = thisObject;
    }

    public Closure(Object owner) {
        this(owner, null);
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public V call(Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (V) getMetaClass().invokeMethod(this, "doCall", args);
    }

    public V call(final Object arguments) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        return call(new Object[]{arguments});
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public V call() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        final Object[] NOARGS = EMPTY_OBJECT_ARRAY;
        return call(NOARGS);
    }

    public Object getOwner() {
        return this.owner;
    }

}
