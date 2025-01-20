package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Depth_001_TrampolineGadget extends GleipnerObject {
    public Depth_001_LinkGadget1 linkGadget;

    public Depth_001_TrampolineGadget () {
    }

    public Depth_001_TrampolineGadget (Depth_001_LinkGadget1 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}