package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

public class Taint_007_FP_Trampoline extends GleipnerObject {

    public Nested nested;

    private static final String TRUE = "true";
    private static final String FALSE = "false";

    public Taint_007_FP_Trampoline() {
    }

    public Taint_007_FP_Trampoline(Nested nested) {
        this.nested = nested;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Nested n1 = nested.get(0);
        Nested n2 = nested.get(1);
        Nested n3 = nested.get(2);

        Nested sink = nested.get(3);
        Nested taint = nested.get(4);

        // not n1 and n2 and not n3
        if (n1.invoke("").equals(FALSE) && n2.invoke("").equals(TRUE) && n3.invoke("").equals(FALSE)) {
            // n1 or n3
            if (n1.invoke("").equals(TRUE) || n3.invoke("").equals(TRUE)) {
                sink.invoke(taint.invoke(""));
            }
        }







        return super.hashCode();
    }
}
