package gleipner.chains.lambda;


import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;
import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;

public class Lambda_002_FP_Trampoline extends GleipnerObject implements Serializable {

    public SinkGadget sinkGadget;
    public String taint;

    public Lambda_002_FP_Trampoline() {
    }

    public Lambda_002_FP_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {

        ILambdaExpr iface = (i) -> sinkGadget.sinkMethod("");
        iface.method(taint);

        return super.hashCode();
    }
}
