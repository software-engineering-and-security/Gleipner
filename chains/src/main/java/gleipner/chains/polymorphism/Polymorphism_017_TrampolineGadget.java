package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_017_TrampolineGadget extends GleipnerObject {
    public Polymorphism_017_Parent linkGadget;

    public Polymorphism_017_TrampolineGadget () {
    }

    public Polymorphism_017_TrampolineGadget (Polymorphism_017_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}