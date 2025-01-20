package gleipner.chains.ysoserial.c3p0;

import java.io.IOException;
import java.io.Serializable;

public interface IndirectlySerialized extends Serializable {
    public Object getObject() throws ClassNotFoundException, IOException;
}
