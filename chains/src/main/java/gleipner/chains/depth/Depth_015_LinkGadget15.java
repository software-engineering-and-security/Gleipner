package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_015_LinkGadget15 implements Serializable {
    public Depth_015_SinkGadget sinkGadget;

    public Depth_015_LinkGadget15 () {
    }

    public Depth_015_LinkGadget15 (Depth_015_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}