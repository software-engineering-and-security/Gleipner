package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_014_LinkGadget14 implements Serializable {
    public Depth_014_SinkGadget sinkGadget;

    public Depth_014_LinkGadget14 () {
    }

    public Depth_014_LinkGadget14 (Depth_014_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}