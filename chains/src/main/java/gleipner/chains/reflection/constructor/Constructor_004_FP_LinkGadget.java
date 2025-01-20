package gleipner.chains.reflection.constructor;

import gleipner.core.SinkGadget;

public class Constructor_004_FP_LinkGadget {

    protected Constructor_004_FP_LinkGadget(String taint, SinkGadget sinkGadget) {
        sinkGadget.sinkMethod(taint);
    }
}
