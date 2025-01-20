package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_001_TrampolineGadget extends GleipnerObject {
    public Polymorphism_001_Parent linkGadget;

    public Polymorphism_001_TrampolineGadget () {
    }

    public Polymorphism_001_TrampolineGadget (Polymorphism_001_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}