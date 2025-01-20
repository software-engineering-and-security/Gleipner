package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_019_LinkGadget17 implements Serializable {
    public Depth_019_LinkGadget18 linkGadget;

    public Depth_019_LinkGadget17 () {
    }

    public Depth_019_LinkGadget17 (Depth_019_LinkGadget18 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}