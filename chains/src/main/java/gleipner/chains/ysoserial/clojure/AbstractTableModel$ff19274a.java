package gleipner.chains.ysoserial.clojure;

import gleipner.core.GleipnerObject;

import java.util.Map;

public class AbstractTableModel$ff19274a extends GleipnerObject {

    private volatile Map __clojureFnMap;

    public AbstractTableModel$ff19274a() {

    }

    public void __initClojureFnMappings(Map map) {
        this.__clojureFnMap = map;
    }

    @Override
    public int hashCode() {
        Object function = RT.get(this.__clojureFnMap, "hashCode");
        return function != null ? ((Number)((IFn)function).invoke(this)).intValue() : super.hashCode();
    }



}
