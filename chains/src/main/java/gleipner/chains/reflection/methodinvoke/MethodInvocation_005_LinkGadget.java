package gleipner.chains.reflection.methodinvoke;

import gleipner.core.SinkGadget;

import java.io.Serializable;

public class MethodInvocation_005_LinkGadget implements Serializable {

    public SinkGadget sinkGadget;

    public MethodInvocation_005_LinkGadget(SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    public void invokeSink(String taint) {
        this.sinkGadget.sinkMethod(taint);
    }

}
