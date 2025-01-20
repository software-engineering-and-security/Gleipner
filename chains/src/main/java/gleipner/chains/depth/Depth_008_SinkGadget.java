package gleipner.chains.depth;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.SinkAccessPaths;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.SinkGadget;
import java.io.Serializable;


public class Depth_008_SinkGadget implements Serializable {

    public String tainted;
    public SinkGadget sinkGadget;

    public Depth_008_SinkGadget () {
    }

    public Depth_008_SinkGadget (String tainted, SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
        this.tainted = tainted;
    }

    @ChainLength(11)
@SinkAccessPaths(1)
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.sinkMethod(tainted);
    }
}