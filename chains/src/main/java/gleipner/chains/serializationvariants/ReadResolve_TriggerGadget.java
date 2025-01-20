package gleipner.chains.serializationvariants;

import gleipner.core.SinkGadget;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class ReadResolve_TriggerGadget implements Serializable {

    public SinkGadget sinkGadget;
    public String taint;

    public ReadResolve_TriggerGadget(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }


    private Object readResolve() throws ObjectStreamException {
        sinkGadget.sinkMethod(taint);
        return this;
    }

}
