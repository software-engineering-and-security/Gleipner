package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_015_LinkGadget8 implements Serializable {
    public Depth_015_LinkGadget9 linkGadget;

    public Depth_015_LinkGadget8 () {
    }

    public Depth_015_LinkGadget8 (Depth_015_LinkGadget9 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}