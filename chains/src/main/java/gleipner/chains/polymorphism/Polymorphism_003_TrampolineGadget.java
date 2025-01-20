package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_003_TrampolineGadget extends GleipnerObject {
    public Polymorphism_003_Parent linkGadget;

    public Polymorphism_003_TrampolineGadget () {
    }

    public Polymorphism_003_TrampolineGadget (Polymorphism_003_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}