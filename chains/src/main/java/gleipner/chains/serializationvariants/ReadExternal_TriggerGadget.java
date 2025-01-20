package gleipner.chains.serializationvariants;

import gleipner.core.SinkGadget;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ReadExternal_TriggerGadget implements Externalizable {

    public SinkGadget sinkGadget;
    public String taint;

    public ReadExternal_TriggerGadget() {}

    public ReadExternal_TriggerGadget(SinkGadget sinkGadget, String taint) {
        this.taint = taint;
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(sinkGadget);
        out.writeUTF(taint);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        this.sinkGadget = (SinkGadget) in.readObject();
        this.taint = in.readUTF();

        sinkGadget.sinkMethod(taint);
    }
}
