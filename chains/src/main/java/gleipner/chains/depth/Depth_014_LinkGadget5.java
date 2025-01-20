package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Depth_014_LinkGadget5 implements Serializable {
    public Depth_014_LinkGadget6 linkGadget;

    public Depth_014_LinkGadget5 () {
    }

    public Depth_014_LinkGadget5 (Depth_014_LinkGadget6 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}