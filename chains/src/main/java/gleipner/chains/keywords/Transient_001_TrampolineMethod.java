package gleipner.chains.keywords;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Transient_001_TrampolineMethod extends GleipnerObject {

    private transient String taint;
    private transient SinkGadget sinkGadget;

    public Transient_001_TrampolineMethod(String taint, SinkGadget sinkGadget) {
        this.taint = taint;
        this.sinkGadget = sinkGadget;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(sinkGadget);
        out.writeUTF(taint);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.sinkGadget = (SinkGadget) in.readObject();
        this.taint = in.readUTF();
    }

    @Override
    public int hashCode() {
        sinkGadget.sinkMethod(taint);
        return super.hashCode();
    }



}
