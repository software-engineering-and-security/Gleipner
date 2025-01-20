package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_019_LinkGadget19 implements Serializable {
    public Depth_019_SinkGadget sinkGadget;

    public Depth_019_LinkGadget19 () {
    }

    public Depth_019_LinkGadget19 (Depth_019_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}