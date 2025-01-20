package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_006_TrampolineGadget extends GleipnerObject {
    public Polymorphism_006_Parent linkGadget;

    public Polymorphism_006_TrampolineGadget () {
    }

    public Polymorphism_006_TrampolineGadget (Polymorphism_006_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}