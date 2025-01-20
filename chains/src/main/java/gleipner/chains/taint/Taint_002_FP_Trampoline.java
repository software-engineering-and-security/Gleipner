package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

public class Taint_002_FP_Trampoline extends GleipnerObject {

    Nested tuple;

    public Taint_002_FP_Trampoline(Nested tuple) {
        this.tuple = tuple;
    }


    @Override
    @FalsePositive
    public int hashCode() {
        // impossible to fulfill, because n1 == n2, therefore NestedSink.invoke(null) --> no SinkGadget invocation
        if (tuple.get(0).equals(tuple.get(1))) {
            Nested n1 = tuple.get(0);
            Nested n2 = tuple.get(1);
            n1.invoke(n2.invoke(null));
        }

        return super.hashCode();
    }
}
