package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Depth_013_TrampolineGadget extends GleipnerObject {
    public Depth_013_LinkGadget1 linkGadget;

    public Depth_013_TrampolineGadget () {
    }

    public Depth_013_TrampolineGadget (Depth_013_LinkGadget1 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}