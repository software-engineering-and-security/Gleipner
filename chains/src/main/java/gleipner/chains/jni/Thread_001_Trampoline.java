package gleipner.chains.jni;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.InterProcedural;

public class Thread_001_Trampoline extends GleipnerObject {

    public String tainted;
    public SinkGadget sinkGadget;

    public Thread_001_Trampoline() {
    }

    public Thread_001_Trampoline(String tainted, SinkGadget sinkGadget) {
        this.tainted = tainted;
        this.sinkGadget = sinkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {

        Thread_001_SinkGadget runnable = new Thread_001_SinkGadget(tainted, sinkGadget);
        Thread thread = new Thread(runnable);
        // Thread.start0 is natively implemented
        thread.start();
        return super.hashCode();
    }
}
