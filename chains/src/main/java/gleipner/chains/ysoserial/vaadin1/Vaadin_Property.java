package gleipner.chains.ysoserial.vaadin1;

import java.io.Serializable;

public interface Vaadin_Property<T> extends Serializable {

    public T getValue();

    boolean isReadOnly();



}
