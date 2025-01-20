package gleipner.chains.ysoserial.aspectjweaver;

import gleipner.core.GleipnerObject;

import java.io.Serializable;
import java.util.Map;

public class TiedMapEntry extends GleipnerObject implements Map.Entry {

    private final Map map;
    private final Object key;


    public TiedMapEntry(Map map, Object key) {
        super();
        this.map = map;
        this.key = key;
    }


    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return map.get(key);
    }

    @Override
    public Object setValue(Object value) {
        return null;
    }

    public int hashCode() {
        Object value = getValue();
        return value.hashCode();
    }
}
