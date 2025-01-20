package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_010_TrampolineGadget extends GleipnerObject {
    public Polymorphism_010_Parent linkGadget;

    public Polymorphism_010_TrampolineGadget () {
    }

    public Polymorphism_010_TrampolineGadget (Polymorphism_010_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}