package gleipner.chains.reflection.constructor;

import gleipner.core.SinkGadget;

public class Constructor_003_LinkGadget {

    public Constructor_003_LinkGadget(String taint, SinkGadget sinkGadget) {
        sinkGadget.sinkMethod(taint);
    }
}
