package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_018_LinkGadget18 implements Serializable {
    public Depth_018_SinkGadget sinkGadget;

    public Depth_018_LinkGadget18 () {
    }

    public Depth_018_LinkGadget18 (Depth_018_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}