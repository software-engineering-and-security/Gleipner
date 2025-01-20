package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_006_LinkGadget6 implements Serializable {
    public Depth_006_SinkGadget sinkGadget;

    public Depth_006_LinkGadget6 () {
    }

    public Depth_006_LinkGadget6 (Depth_006_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.linkMethod();
    }

}