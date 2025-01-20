package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.core.GleipnerObject;

public class Taint_001_Trampoline extends GleipnerObject {

    private Nested nested;
    private String arg;

    public Taint_001_Trampoline(Nested nested, String arg) {
        this.nested = nested;
        this.arg = arg;
    }

    @Override
    public int hashCode() {

        Nested n1, n2, n3;

        n1 = nested.get(0);
        n2 = n1.get(0);
        n3 = n2.get(0);
        n3.invoke(arg);

        return super.hashCode();
    }
}
