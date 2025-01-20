package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_002_LinkGadget2 implements Serializable {
    public Depth_002_SinkGadget sinkGadget;

    public Depth_002_LinkGadget2 () {
    }

    public Depth_002_LinkGadget2 (Depth_002_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}