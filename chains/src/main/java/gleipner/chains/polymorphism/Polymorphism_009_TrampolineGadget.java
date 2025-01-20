package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_009_TrampolineGadget extends GleipnerObject {
    public Polymorphism_009_Parent linkGadget;

    public Polymorphism_009_TrampolineGadget () {
    }

    public Polymorphism_009_TrampolineGadget (Polymorphism_009_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}