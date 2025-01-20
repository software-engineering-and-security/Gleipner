package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_017_LinkGadget17 implements Serializable {
    public Depth_017_SinkGadget sinkGadget;

    public Depth_017_LinkGadget17 () {
    }

    public Depth_017_LinkGadget17 (Depth_017_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}