package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_019_LinkGadget3 implements Serializable {
    public Depth_019_LinkGadget4 linkGadget;

    public Depth_019_LinkGadget3 () {
    }

    public Depth_019_LinkGadget3 (Depth_019_LinkGadget4 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}