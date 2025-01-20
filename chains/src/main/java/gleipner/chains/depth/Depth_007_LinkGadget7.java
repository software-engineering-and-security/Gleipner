package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_007_LinkGadget7 implements Serializable {
    public Depth_007_SinkGadget sinkGadget;

    public Depth_007_LinkGadget7 () {
    }

    public Depth_007_LinkGadget7 (Depth_007_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}