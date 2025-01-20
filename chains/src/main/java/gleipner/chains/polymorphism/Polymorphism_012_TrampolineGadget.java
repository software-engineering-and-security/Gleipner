package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_012_TrampolineGadget extends GleipnerObject {
    public Polymorphism_012_Parent linkGadget;

    public Polymorphism_012_TrampolineGadget () {
    }

    public Polymorphism_012_TrampolineGadget (Polymorphism_012_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}