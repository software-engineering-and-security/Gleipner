package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_012_LinkGadget11 implements Serializable {
    public Depth_012_LinkGadget12 linkGadget;

    public Depth_012_LinkGadget11 () {
    }

    public Depth_012_LinkGadget11 (Depth_012_LinkGadget12 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}