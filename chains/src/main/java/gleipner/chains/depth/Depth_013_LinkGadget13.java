package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_013_LinkGadget13 implements Serializable {
    public Depth_013_SinkGadget sinkGadget;

    public Depth_013_LinkGadget13 () {
    }

    public Depth_013_LinkGadget13 (Depth_013_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}