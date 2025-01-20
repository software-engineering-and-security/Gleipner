package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_008_LinkGadget8 implements Serializable {
    public Depth_008_SinkGadget sinkGadget;

    public Depth_008_LinkGadget8 () {
    }

    public Depth_008_LinkGadget8 (Depth_008_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}