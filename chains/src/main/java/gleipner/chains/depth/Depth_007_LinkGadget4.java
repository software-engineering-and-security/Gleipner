package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_007_LinkGadget4 implements Serializable {
    public Depth_007_LinkGadget5 linkGadget;

    public Depth_007_LinkGadget4 () {
    }

    public Depth_007_LinkGadget4 (Depth_007_LinkGadget5 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}