package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_010_LinkGadget10 implements Serializable {
    public Depth_010_SinkGadget sinkGadget;

    public Depth_010_LinkGadget10 () {
    }

    public Depth_010_LinkGadget10 (Depth_010_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}