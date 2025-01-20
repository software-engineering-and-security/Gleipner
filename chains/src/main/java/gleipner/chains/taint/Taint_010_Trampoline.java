package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.core.GleipnerObject;

public class Taint_010_Trampoline extends GleipnerObject {

    public Nested nested;

    public Taint_010_Trampoline() {
    }

    public Taint_010_Trampoline(Nested nested) {
        this.nested = nested;
    }

    @Override
    public int hashCode() {

        Nested n1 = nested.get(0);
        Nested n2 = nested.get(1);
        if (n2.equals(n2.get(0))) return 0;
        if (n1.equals(n1.get(0))) return 0;
        n1 = n1.get(0);
        n2 = n2.get(0);
        if (!n1.equals(n2)) return 0;
        if (n2.equals(n2.get(0))) return 0;
        if (n1.equals(n1.get(0))) return 0;
        n1 = n1.get(0);
        n2 = n2.get(0);
        if (!n1.equals(n2)) return 0;
        if (n2.equals(n2.get(0))) return 0;
        if (n1.equals(n1.get(0))) return 0;
        n1 = n1.get(0);
        n2 = n2.get(0);
        if (!n1.equals(n2)) return 0;
        if (n2.equals(n2.get(0))) return 0;
        if (n1.equals(n1.get(0))) return 0;
        n1 = n1.get(0);
        n2 = n2.get(0);
        if (!n1.equals(n2)) return 0;
        if (n2.equals(n2.get(0))) return 0;
        if (n1.equals(n1.get(0))) return 0;
        n1 = n1.get(0);
        n2 = n2.get(0);
        if (!n1.equals(n2)) return 0;
        if (n2.equals(n2.get(0))) return 0;
        if (n1.equals(n1.get(0))) return 0;
        n1 = n1.get(0);
        n2 = n2.get(0);
        if (!n1.equals(n2)) return 0;
        nested.get(0).invoke(nested.get(2).invoke(null));


        return super.hashCode();
    }
}

