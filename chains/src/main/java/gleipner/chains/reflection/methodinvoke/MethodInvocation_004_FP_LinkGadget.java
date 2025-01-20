package gleipner.chains.reflection.methodinvoke;

import gleipner.core.SinkGadget;

public class MethodInvocation_004_FP_LinkGadget {

    public void invokeSink(SinkGadget sinkGadget, String taint) {
        sinkGadget.sinkMethod(taint);
    }

}
