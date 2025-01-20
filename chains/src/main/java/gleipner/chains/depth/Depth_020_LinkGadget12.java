package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_020_LinkGadget12 implements Serializable {
    public Depth_020_LinkGadget13 linkGadget;

    public Depth_020_LinkGadget12 () {
    }

    public Depth_020_LinkGadget12 (Depth_020_LinkGadget13 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}