package gleipner.chains.lambda;


import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.InterProcedural;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Lambda_001_Trampoline extends GleipnerObject implements Serializable {

    public SinkGadget sinkGadget;
    public String taint;

    public Lambda_001_Trampoline() {
    }

    public Lambda_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }


    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {

        ILambdaExpr iface = (i) -> sinkGadget.sinkMethod(i);
        iface.method(taint);

        return super.hashCode();
    }
}
