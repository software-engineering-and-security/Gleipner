package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_007_LinkGadget6 implements Serializable {
    public Depth_007_LinkGadget7 linkGadget;

    public Depth_007_LinkGadget6 () {
    }

    public Depth_007_LinkGadget6 (Depth_007_LinkGadget7 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}