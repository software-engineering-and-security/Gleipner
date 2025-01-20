package gleipner.chains.ysoserial.aspectjweaver;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapDecorator implements Map {

    protected transient Map map;

    protected AbstractMapDecorator() {
        super();
    }

    public AbstractMapDecorator(Map map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null");
        }
        this.map = map;
    }

    protected Map getMap() {
        return map;
    }

    //-----------------------------------------------------------------------
    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public Set entrySet() {
        return map.entrySet();
    }

    public Object get(Object key) {
        return map.get(key);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set keySet() {
        return map.keySet();
    }

    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    public void putAll(Map mapToCopy) {
        map.putAll(mapToCopy);
    }

    public Object remove(Object key) {
        return map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public Collection values() {
        return map.values();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        return map.equals(object);
    }

    public int hashCode() {
        return map.hashCode();
    }

    public String toString() {
        return map.toString();
    }

}
