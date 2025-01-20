package gleipner.chains.multipath;

import gleipner.core.annotations.InterProcedural;
import java.io.Serializable;

public class Multipath_001_LinkGadget3 implements Serializable {
    public Multipath_001_SinkGadget linkGadget;

    public Multipath_001_LinkGadget3 () {
    }

    public Multipath_001_LinkGadget3 (Multipath_001_SinkGadget linkGadget) {
        this.linkGadget = linkGadget;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        linkGadget.linkMethod();
    }

}