package gleipner.chains.serializationvariants;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ReadObjectNoData_TriggerGadget implements Serializable {


    private ReadObjectNoData_Child child;
    private SinkGadget sinkGadget;
    private String taint;

    public static ReadObjectNoData_TriggerGadget instance;

    public ReadObjectNoData_TriggerGadget(ReadObjectNoData_Child child, SinkGadget sinkGadget, String taint) {
        this.child = child;
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    public void invoke() {
        this.sinkGadget.sinkMethod(taint);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(sinkGadget);
        oos.writeUTF(taint);
        oos.writeObject(child);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        this.sinkGadget = (SinkGadget) ois.readObject();
        this.taint = ois.readUTF();
        ReadObjectNoData_TriggerGadget.instance = this;
        ois.readObject();
    }
}
