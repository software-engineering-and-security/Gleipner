package gleipner.chains.basic;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;

public class BasicLinkGadget extends GleipnerObject implements Serializable{
    public BasicSinkGadget basicSinkGadget;

    public BasicLinkGadget() {}

    public BasicLinkGadget(BasicSinkGadget sinkGadget) {
        this.basicSinkGadget = sinkGadget;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        basicSinkGadget.load();
        return super.hashCode();
    }

}
