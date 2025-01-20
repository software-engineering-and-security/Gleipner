package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_004_TrampolineGadget extends GleipnerObject {
    public Polymorphism_004_Parent linkGadget;

    public Polymorphism_004_TrampolineGadget () {
    }

    public Polymorphism_004_TrampolineGadget (Polymorphism_004_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}