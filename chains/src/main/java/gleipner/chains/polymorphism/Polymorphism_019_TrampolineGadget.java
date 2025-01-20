package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_019_TrampolineGadget extends GleipnerObject {
    public Polymorphism_019_Parent linkGadget;

    public Polymorphism_019_TrampolineGadget () {
    }

    public Polymorphism_019_TrampolineGadget (Polymorphism_019_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}