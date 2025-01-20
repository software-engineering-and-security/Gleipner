package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_015_TrampolineGadget extends GleipnerObject {
    public Polymorphism_015_Parent linkGadget;

    public Polymorphism_015_TrampolineGadget () {
    }

    public Polymorphism_015_TrampolineGadget (Polymorphism_015_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}