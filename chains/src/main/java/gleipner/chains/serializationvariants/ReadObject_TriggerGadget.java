package gleipner.chains.serializationvariants;

import gleipner.core.SinkGadget;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ReadObject_TriggerGadget implements Serializable {

    public SinkGadget sinkGadget;
    public String taint;

    public ReadObject_TriggerGadget(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        sinkGadget.sinkMethod(taint);
    }

}
