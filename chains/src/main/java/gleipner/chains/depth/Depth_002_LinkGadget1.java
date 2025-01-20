package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_002_LinkGadget1 implements Serializable {
    public Depth_002_LinkGadget2 linkGadget;

    public Depth_002_LinkGadget1 () {
    }

    public Depth_002_LinkGadget1 (Depth_002_LinkGadget2 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}