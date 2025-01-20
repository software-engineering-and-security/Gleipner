package gleipner.chains.depth;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Depth_005_TrampolineGadget extends GleipnerObject {
    public Depth_005_LinkGadget1 linkGadget;

    public Depth_005_TrampolineGadget () {
    }

    public Depth_005_TrampolineGadget (Depth_005_LinkGadget1 linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}