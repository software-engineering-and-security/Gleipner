package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_008_TrampolineGadget extends GleipnerObject {
    public Polymorphism_008_Parent linkGadget;

    public Polymorphism_008_TrampolineGadget () {
    }

    public Polymorphism_008_TrampolineGadget (Polymorphism_008_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}