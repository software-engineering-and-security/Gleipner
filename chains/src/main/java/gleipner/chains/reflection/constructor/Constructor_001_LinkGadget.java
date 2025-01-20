package gleipner.chains.reflection.constructor;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.SinkAccessPaths;

public class Constructor_001_LinkGadget {

    @InterProcedural(InterProcedural.INIT)
    @ChainLength(3)
    @SinkAccessPaths(1)
    public Constructor_001_LinkGadget(SinkGadget sinkGadget, String taint) {
        sinkGadget.sinkMethod(taint);
    }


}
