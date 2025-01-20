package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_019_LinkGadget18 implements Serializable {
    public Depth_019_LinkGadget19 linkGadget;

    public Depth_019_LinkGadget18 () {
    }

    public Depth_019_LinkGadget18 (Depth_019_LinkGadget19 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}