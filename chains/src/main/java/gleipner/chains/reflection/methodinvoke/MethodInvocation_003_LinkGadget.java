package gleipner.chains.reflection.methodinvoke;

import gleipner.core.SinkGadget;

import java.io.Serializable;

public class MethodInvocation_003_LinkGadget {

    public static void invokeSink(SinkGadget sinkGadget, String taint) {
        sinkGadget.sinkMethod(taint);
    }




}
