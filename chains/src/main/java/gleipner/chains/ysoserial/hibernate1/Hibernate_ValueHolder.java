package gleipner.chains.ysoserial.hibernate1;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.IntraProcedural;

public class Hibernate_ValueHolder<T> {

    public static interface DeferredInitializer<T> {
        public T initialize();
    }

    private final DeferredInitializer<T> valueInitializer;
    private T value;
    public Hibernate_ValueHolder(DeferredInitializer<T> valueInitializer) {
        this.valueInitializer = valueInitializer;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @IntraProcedural(IntraProcedural.CONTROL_FLOW)
    public T getValue() {
        if ( value == null ) {
            value = valueInitializer.initialize();
        }
        return value;
    }
}
