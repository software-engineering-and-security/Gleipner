package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_016_LinkGadget11 implements Serializable {
    public Depth_016_LinkGadget12 linkGadget;

    public Depth_016_LinkGadget11 () {
    }

    public Depth_016_LinkGadget11 (Depth_016_LinkGadget12 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}