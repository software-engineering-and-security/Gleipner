package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_005_LinkGadget5 implements Serializable {
    public Depth_005_SinkGadget sinkGadget;

    public Depth_005_LinkGadget5 () {
    }

    public Depth_005_LinkGadget5 (Depth_005_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}