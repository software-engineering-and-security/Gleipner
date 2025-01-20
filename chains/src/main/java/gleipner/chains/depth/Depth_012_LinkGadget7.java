package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_012_LinkGadget7 implements Serializable {
    public Depth_012_LinkGadget8 linkGadget;

    public Depth_012_LinkGadget7 () {
    }

    public Depth_012_LinkGadget7 (Depth_012_LinkGadget8 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}