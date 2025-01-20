package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.core.GleipnerObject;

public class Taint_004_Trampoline extends GleipnerObject {

    private Nested n1;
    private Nested n2;



    public Taint_004_Trampoline(Nested n1, Nested n2) {
        this.n1 = n1;
        this.n2 = n2;
    }


    @Override
    public int hashCode() {

        int comparisonDepth = 3;
        int i;

        Nested cmp1 = n1;
        Nested cmp2 = n2;

        for (i = 0; i < comparisonDepth; i++) {
            if (cmp1 == null || cmp2 == null) return 0;

            if (! cmp1.get(i).equals(cmp2.get(i))) {
                return 0;
            }
            cmp1 = cmp1.get(i);
            cmp2 = cmp2.get(i);
        }

        String cmd = n1.get(1).invoke(null);
        n2.get(1).invoke(cmd);


        return super.hashCode();
    }
}
