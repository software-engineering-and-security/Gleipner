package gleipner.chains.reflection.methodinvoke;

import gleipner.core.SinkGadget;

import java.io.Serializable;

public class MethodInvocation_008_FP_LinkGadget implements Serializable {

    public SinkGadget sinkGadget;
    public String taint;

    public MethodInvocation_008_FP_LinkGadget(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    private void invokeSink() {
        this.sinkGadget.sinkMethod(taint);
    }

}
