package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_004_LinkGadget4 implements Serializable {
    public Depth_004_SinkGadget sinkGadget;

    public Depth_004_LinkGadget4 () {
    }

    public Depth_004_LinkGadget4 (Depth_004_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}