package gleipner.chains.ysoserial.vaadin1;

public abstract class Vaadin_AbstractProperty<T> implements Vaadin_Property<T> {

    private boolean readOnly;

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

}
