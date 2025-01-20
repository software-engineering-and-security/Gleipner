package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.chains.taint.nested.NestedString;
import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

public class Taint_005_FP_Trampoline extends GleipnerObject {

    public Nested n1;
    public Nested n2;
    public Nested n3;

    public Taint_005_FP_Trampoline() {
    }

    public Taint_005_FP_Trampoline(Nested n1, Nested n2, Nested n3) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Nested n4 = new Nested(4);
        n4.set(0, n1);
        n4.set(1, n2);
        n4.set(2, n3);
        n4.set(3, new NestedString(""));

        n4.set(0, n4.get(3));
        n4.set(2, n4.get(0));

        n4.get(1).invoke(n4.get(2).invoke(""));

        return super.hashCode();
    }


}
