package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_020_LinkGadget20 implements Serializable {
    public Depth_020_SinkGadget sinkGadget;

    public Depth_020_LinkGadget20 () {
    }

    public Depth_020_LinkGadget20 (Depth_020_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}