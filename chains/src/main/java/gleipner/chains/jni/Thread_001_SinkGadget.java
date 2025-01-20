package gleipner.chains.jni;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.SinkAccessPaths;

public class Thread_001_SinkGadget implements Runnable{

    private String tainted;
    private SinkGadget sinkGadget;

    public Thread_001_SinkGadget(String tainted, SinkGadget sinkGadget) {
        this.tainted = tainted;
        this.sinkGadget = sinkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.JNI)
    @ChainLength(3)
    @SinkAccessPaths(1)
    public void run() {
        sinkGadget.sinkMethod(tainted);
    }

}
