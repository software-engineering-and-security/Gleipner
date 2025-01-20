package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.chains.taint.nested.NestedString;
import gleipner.core.GleipnerObject;

public class Taint_003_Trampoline extends GleipnerObject {

    private Nested tuple;
    private final Nested constantNested;

    public Taint_003_Trampoline(Nested tuple, String value) {
        this.tuple = tuple;
        this.constantNested = new NestedString(value);
    }

    @Override
    public int hashCode() {
        String taint = constantNested.invoke(null);

        Nested n1 = tuple.get(0).get(1);
        Nested n2 = tuple.get(0).get(0);
        Nested n3 = tuple.get(1);

        if (n1.equals(n3)) {
            n2.invoke(taint);
        }

        return super.hashCode();
    }
}
