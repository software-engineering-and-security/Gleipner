package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_016_LinkGadget16 implements Serializable {
    public Depth_016_SinkGadget sinkGadget;

    public Depth_016_LinkGadget16 () {
    }

    public Depth_016_LinkGadget16 (Depth_016_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}