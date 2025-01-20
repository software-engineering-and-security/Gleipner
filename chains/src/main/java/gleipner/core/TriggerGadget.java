package gleipner.core;

import gleipner.core.annotations.InterProcedural;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class TriggerGadget implements Serializable {

    public GleipnerObject o;

    public TriggerGadget() {
    }

    @InterProcedural(InterProcedural.READ_OBJECT)
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        o.hashCode();
    }
}
