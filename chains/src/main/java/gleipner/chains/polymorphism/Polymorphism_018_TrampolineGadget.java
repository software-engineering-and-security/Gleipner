package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_018_TrampolineGadget extends GleipnerObject {
    public Polymorphism_018_Parent linkGadget;

    public Polymorphism_018_TrampolineGadget () {
    }

    public Polymorphism_018_TrampolineGadget (Polymorphism_018_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}