package gleipner.chains.reflection.methodinvoke;

import gleipner.core.SinkGadget;

import java.io.Serializable;

public class MethodInvocation_006_FP_LinkGadget implements Serializable{

    public SinkGadget sinkGadget;

    public MethodInvocation_006_FP_LinkGadget(SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    public void invokeSink(String taint, boolean doInvoke) {
        if (doInvoke)
            this.sinkGadget.sinkMethod(taint);
    }

}
