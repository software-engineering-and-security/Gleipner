package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_019_LinkGadget16 implements Serializable {
    public Depth_019_LinkGadget17 linkGadget;

    public Depth_019_LinkGadget16 () {
    }

    public Depth_019_LinkGadget16 (Depth_019_LinkGadget17 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}