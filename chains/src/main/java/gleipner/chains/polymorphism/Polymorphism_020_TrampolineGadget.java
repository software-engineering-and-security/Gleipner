package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_020_TrampolineGadget extends GleipnerObject {
    public Polymorphism_020_Parent linkGadget;

    public Polymorphism_020_TrampolineGadget () {
    }

    public Polymorphism_020_TrampolineGadget (Polymorphism_020_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}