package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_011_TrampolineGadget extends GleipnerObject {
    public Polymorphism_011_Parent linkGadget;

    public Polymorphism_011_TrampolineGadget () {
    }

    public Polymorphism_011_TrampolineGadget (Polymorphism_011_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}