package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_017_LinkGadget11 implements Serializable {
    public Depth_017_LinkGadget12 linkGadget;

    public Depth_017_LinkGadget11 () {
    }

    public Depth_017_LinkGadget11 (Depth_017_LinkGadget12 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}