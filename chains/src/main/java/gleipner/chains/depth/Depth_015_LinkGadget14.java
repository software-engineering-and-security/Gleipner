package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_015_LinkGadget14 implements Serializable {
    public Depth_015_LinkGadget15 linkGadget;

    public Depth_015_LinkGadget14 () {
    }

    public Depth_015_LinkGadget14 (Depth_015_LinkGadget15 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}