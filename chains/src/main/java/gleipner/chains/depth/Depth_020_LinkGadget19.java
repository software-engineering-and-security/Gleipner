package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_020_LinkGadget19 implements Serializable {
    public Depth_020_LinkGadget20 linkGadget;

    public Depth_020_LinkGadget19 () {
    }

    public Depth_020_LinkGadget19 (Depth_020_LinkGadget20 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}