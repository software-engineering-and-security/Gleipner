package gleipner.chains.ysoserial.hibernate1;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.InterProcedural;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public final class Hibernate_TypedValue extends GleipnerObject {


    private final Hibernate_Type type;
    private final Object value;
    private transient Hibernate_ValueHolder<Integer> hashcode;

    public Hibernate_TypedValue(final Hibernate_Type type, final Object value) {
        this.type = type;
        this.value = value;
        initTransients();
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        hashcode.getValue();
        return super.hashCode();
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        initTransients();
    }

    private void initTransients() {
        this.hashcode = new Hibernate_ValueHolder<Integer>( new Hibernate_ValueHolder.DeferredInitializer<Integer>() {
            @Override
            @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
            public Integer initialize() {
                return value == null ? 0 : type.getHashCode( value );
            }
        } );
    }

}
