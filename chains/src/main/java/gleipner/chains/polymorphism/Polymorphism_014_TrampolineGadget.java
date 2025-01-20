package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_014_TrampolineGadget extends GleipnerObject {
    public Polymorphism_014_Parent linkGadget;

    public Polymorphism_014_TrampolineGadget () {
    }

    public Polymorphism_014_TrampolineGadget (Polymorphism_014_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}