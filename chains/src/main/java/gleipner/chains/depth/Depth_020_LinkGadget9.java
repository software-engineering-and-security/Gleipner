package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_020_LinkGadget9 implements Serializable {
    public Depth_020_LinkGadget10 linkGadget;

    public Depth_020_LinkGadget9 () {
    }

    public Depth_020_LinkGadget9 (Depth_020_LinkGadget10 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}