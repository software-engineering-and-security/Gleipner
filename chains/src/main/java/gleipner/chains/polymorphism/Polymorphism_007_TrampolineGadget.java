package gleipner.chains.polymorphism;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.GleipnerObject;

public class Polymorphism_007_TrampolineGadget extends GleipnerObject {
    public Polymorphism_007_Parent linkGadget;

    public Polymorphism_007_TrampolineGadget () {
    }

    public Polymorphism_007_TrampolineGadget (Polymorphism_007_Parent linkGadget) {
        this.linkGadget = linkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        linkGadget.linkMethod();
        return super.hashCode();
    }
}