package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_013_LinkGadget4 implements Serializable {
    public Depth_013_LinkGadget5 linkGadget;

    public Depth_013_LinkGadget4 () {
    }

    public Depth_013_LinkGadget4 (Depth_013_LinkGadget5 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}