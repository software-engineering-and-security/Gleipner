package gleipner.chains.ysoserial.vaadin1;

import java.io.Serializable;
import java.util.Collection;

public interface Vaadin_Item extends Serializable {


    public Vaadin_Property getItemProperty(Object id);


    public Collection<?> getItemPropertyIds();

    boolean addItemProperty(Object id, Vaadin_Property property);


}
