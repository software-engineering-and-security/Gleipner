package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_002_TrampolineGadget extends GleipnerObject {
    public Polymorphism_002_Parent linkGadget;

    public Polymorphism_002_TrampolineGadget () {
    }

    public Polymorphism_002_TrampolineGadget (Polymorphism_002_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}