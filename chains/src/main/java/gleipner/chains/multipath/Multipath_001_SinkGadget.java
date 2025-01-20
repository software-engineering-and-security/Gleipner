package gleipner.chains.multipath;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.SinkAccessPaths;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.SinkGadget;
import java.io.Serializable;


public class Multipath_001_SinkGadget implements Serializable {

    public String tainted;
    public SinkGadget sinkGadget;

    public Multipath_001_SinkGadget () {
    }

    public Multipath_001_SinkGadget (String tainted, SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
        this.tainted = tainted;
    }

    @ChainLength(4)
@SinkAccessPaths(10)
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.sinkMethod(tainted);
    }
}