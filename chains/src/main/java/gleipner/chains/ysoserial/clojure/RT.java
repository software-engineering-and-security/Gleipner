package gleipner.chains.ysoserial.clojure;

import java.util.Map;

public class RT {

    static public Object get(Object coll, Object key) {
        return getFrom(coll, key);
    }

    static Object getFrom(Object coll, Object key) {
        if (coll == null) {
            return null;
        } else if (coll instanceof Map) {
            Map m = (Map) coll;
            return m.get(key);
        }
        return null;
    }
}
