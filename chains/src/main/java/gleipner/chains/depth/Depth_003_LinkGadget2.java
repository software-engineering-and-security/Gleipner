package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_003_LinkGadget2 implements Serializable {
    public Depth_003_LinkGadget3 linkGadget;

    public Depth_003_LinkGadget2 () {
    }

    public Depth_003_LinkGadget2 (Depth_003_LinkGadget3 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}