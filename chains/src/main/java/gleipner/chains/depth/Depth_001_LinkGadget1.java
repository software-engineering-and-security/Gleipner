package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_001_LinkGadget1 implements Serializable {
    public Depth_001_SinkGadget sinkGadget;

    public Depth_001_LinkGadget1 () {
    }

    public Depth_001_LinkGadget1 (Depth_001_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}