package gleipner.chains.ysoserial.vaadin1;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.InterProcedural;

import java.util.*;

public class Vaadin_PropertySetItem extends GleipnerObject implements Vaadin_Item {


    private Map<Object, Vaadin_Property<?>> map = new HashMap<Object, Vaadin_Property<?>>();

    private List<Object> list = new LinkedList<Object>();

    @Override
    public Vaadin_Property getItemProperty(Object id) {
        return map.get(id);
    }

    @Override
    public Collection<?> getItemPropertyIds() {
        return Collections.unmodifiableCollection(list);
    }

    @Override
    public boolean addItemProperty(Object id, Vaadin_Property property) {

        // Null ids are not accepted
        if (id == null) {
            throw new NullPointerException("Item property id can not be null");
        }

        // Can't add a property twice
        if (map.containsKey(id)) {
            return false;
        }

        // Put the property to map
        map.put(id, property);
        list.add(id);

        return true;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @Override
    public int hashCode() {
        String retValue = "";

        for (final Iterator<?> i = getItemPropertyIds().iterator(); i
                .hasNext();) {
            final Object propertyId = i.next();
            retValue += getItemProperty(propertyId).getValue();
            if (i.hasNext()) {
                retValue += " ";
            }
        }

        return super.hashCode();
    }

}
