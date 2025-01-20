package gleipner.chains.basic;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.SinkAccessPaths;

import java.io.Serializable;

public class BasicSinkGadget implements Serializable {

    public SinkGadget sinkGadget;
    public String tainted;

    public BasicSinkGadget() {}
    public BasicSinkGadget(SinkGadget sinkGadget, String tainted) {
        this.sinkGadget = sinkGadget;
        this.tainted = tainted;
    }

    @ChainLength(3)
    @SinkAccessPaths(1)
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void load() {
        sinkGadget.sinkMethod(tainted);
    }

}
