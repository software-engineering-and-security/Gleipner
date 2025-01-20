package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_020_LinkGadget15 implements Serializable {
    public Depth_020_LinkGadget16 linkGadget;

    public Depth_020_LinkGadget15 () {
    }

    public Depth_020_LinkGadget15 (Depth_020_LinkGadget16 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}