package gleipner.chains.basic;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.SinkAccessPaths;

import java.io.*;

public class BasicTriggerGadget implements Serializable {

    public String tainted;
    public SinkGadget sinkGadget;

    @InterProcedural(InterProcedural.READ_OBJECT)
    @ChainLength(1)
    @SinkAccessPaths(1)
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        sinkGadget.sinkMethod(tainted);
    }

}
