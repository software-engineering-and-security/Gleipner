package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_011_LinkGadget11 implements Serializable {
    public Depth_011_SinkGadget sinkGadget;

    public Depth_011_LinkGadget11 () {
    }

    public Depth_011_LinkGadget11 (Depth_011_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}