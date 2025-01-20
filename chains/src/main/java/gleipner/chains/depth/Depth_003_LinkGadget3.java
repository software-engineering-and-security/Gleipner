package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_003_LinkGadget3 implements Serializable {
    public Depth_003_SinkGadget sinkGadget;

    public Depth_003_LinkGadget3 () {
    }

    public Depth_003_LinkGadget3 (Depth_003_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}