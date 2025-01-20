package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_012_LinkGadget12 implements Serializable {
    public Depth_012_SinkGadget sinkGadget;

    public Depth_012_LinkGadget12 () {
    }

    public Depth_012_LinkGadget12 (Depth_012_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}