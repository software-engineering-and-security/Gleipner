package gleipner.chains.polymorphism;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.SinkAccessPaths;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.SinkGadget;
import java.io.Serializable;


public class Polymorphism_006_SinkGadget implements Serializable {

    public String tainted;
    public SinkGadget sinkGadget;

    public Polymorphism_006_SinkGadget () {
    }

    public Polymorphism_006_SinkGadget (String tainted, SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
        this.tainted = tainted;
    }

    @ChainLength(3)
@SinkAccessPaths(1)
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public void linkMethod() {
        sinkGadget.sinkMethod(tainted);
    }
}