package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_016_TrampolineGadget extends GleipnerObject {
    public Polymorphism_016_Parent linkGadget;

    public Polymorphism_016_TrampolineGadget () {
    }

    public Polymorphism_016_TrampolineGadget (Polymorphism_016_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}