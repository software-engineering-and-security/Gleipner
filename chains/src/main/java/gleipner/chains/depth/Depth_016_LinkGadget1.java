package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_016_LinkGadget1 implements Serializable {
    public Depth_016_LinkGadget2 linkGadget;

    public Depth_016_LinkGadget1 () {
    }

    public Depth_016_LinkGadget1 (Depth_016_LinkGadget2 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}