package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_009_LinkGadget9 implements Serializable {
    public Depth_009_SinkGadget sinkGadget;

    public Depth_009_LinkGadget9 () {
    }

    public Depth_009_LinkGadget9 (Depth_009_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}