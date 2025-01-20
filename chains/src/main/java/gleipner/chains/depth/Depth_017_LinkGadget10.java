package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_017_LinkGadget10 implements Serializable {
    public Depth_017_LinkGadget11 linkGadget;

    public Depth_017_LinkGadget10 () {
    }

    public Depth_017_LinkGadget10 (Depth_017_LinkGadget11 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}