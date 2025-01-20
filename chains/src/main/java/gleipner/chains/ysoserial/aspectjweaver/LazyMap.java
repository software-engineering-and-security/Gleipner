package gleipner.chains.ysoserial.aspectjweaver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class LazyMap extends AbstractMapDecorator implements Map, Serializable {

    protected final Transformer factory;

    public static Map decorate(Map map, Transformer factory) {
        return new LazyMap(map, factory);

    }

    protected LazyMap(Map map, Transformer factory) {
        super(map);
        if (factory == null) {
            throw new IllegalArgumentException("Factory must not be null");
        }
        this.factory = factory;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(map);
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        map = (Map) in.readObject();
    }

    public Object get(Object key) {
        // create value for key if key is not currently in the map
        if (map.containsKey(key) == false) {
            //this.apply();
            Object value = factory.transform(key);
            map.put(key, value);
            return value;
        }
        return map.get(key);
    }



}
