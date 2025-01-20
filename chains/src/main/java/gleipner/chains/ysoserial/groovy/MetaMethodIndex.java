package gleipner.chains.ysoserial.groovy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetaMethodIndex {

    public static final Map<Class<?>, Map<String, MetaMethod>> indexMap = new ConcurrentHashMap<>(32);
    static {
        Map<String, MetaMethod> innerMap = new HashMap<>();
        innerMap.put("sinkMethod", new dgm$748());
        indexMap.put(String.class, innerMap);
    }



    public static Object getMethods(final Class<?> cls, String name) {
        Map map = indexMap.get(cls);
        return map == null ? null : map.get(name);
    }

}
