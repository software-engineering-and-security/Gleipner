package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_019_LinkGadget12 implements Serializable {
    public Depth_019_LinkGadget13 linkGadget;

    public Depth_019_LinkGadget12 () {
    }

    public Depth_019_LinkGadget12 (Depth_019_LinkGadget13 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}