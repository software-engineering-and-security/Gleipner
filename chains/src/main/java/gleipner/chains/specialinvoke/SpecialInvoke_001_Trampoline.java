package gleipner.chains.specialinvoke;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class SpecialInvoke_001_Trampoline extends SpecialInvoke_001_SuperClass {

    private final SinkGadget sinkGadget;
    private final String taint;

    public SpecialInvoke_001_Trampoline(SinkGadget sinkGadget, String taint) {
        this.taint = taint;
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void sinkMethod() {
        sinkGadget.sinkMethod(taint);
    }

    @Override
    public int hashCode() {
        this.linkMethod();
        return super.hashCode();
    }
}
