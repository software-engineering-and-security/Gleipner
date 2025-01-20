package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_014_LinkGadget13 implements Serializable {
    public Depth_014_LinkGadget14 linkGadget;

    public Depth_014_LinkGadget13 () {
    }

    public Depth_014_LinkGadget13 (Depth_014_LinkGadget14 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}