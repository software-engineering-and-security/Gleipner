package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_015_LinkGadget5 implements Serializable {
    public Depth_015_LinkGadget6 linkGadget;

    public Depth_015_LinkGadget5 () {
    }

    public Depth_015_LinkGadget5 (Depth_015_LinkGadget6 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}