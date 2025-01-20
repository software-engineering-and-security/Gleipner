package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_009_LinkGadget8 implements Serializable {
    public Depth_009_LinkGadget9 linkGadget;

    public Depth_009_LinkGadget8 () {
    }

    public Depth_009_LinkGadget8 (Depth_009_LinkGadget9 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}