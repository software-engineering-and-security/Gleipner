package gleipner.chains.serializationvariants;

import java.io.ObjectStreamException;
import java.io.Serializable;

public abstract class ReadObjectNoData_Parent implements Serializable {
    private void readObjectNoData() throws ObjectStreamException {
        ReadObjectNoData_TriggerGadget.instance.invoke();
    }
}