package instrumenter.trampolines;

import java.util.Comparator;
import java.util.Map;
import java.io.Serializable;

public class Trampolines implements Serializable {
    Object o;
    Comparator c;

    public void entry() {
        o.hashCode();
        c.compare(o, o);
    }
}
