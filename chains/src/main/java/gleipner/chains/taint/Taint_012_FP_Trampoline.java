package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

import java.util.ArrayList;

public class Taint_012_FP_Trampoline extends GleipnerObject {

    public Nested nested;

    public Taint_012_FP_Trampoline() {
    }

    public Taint_012_FP_Trampoline(Nested nested) {
        this.nested = nested;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        ArrayList<Nested> uniqueList = new ArrayList<>();

        Nested n1 = nested.get(0);
        Nested n2 = nested.get(1);

        for (int i = 0; i < 10; i++) {
            if (contains(uniqueList, n1)) return 0;
            if (!n1.equals(n2)) return 0;

            uniqueList.add(n1);
            n1 = n1.get(0);
            n2 = n2.get(0);
        }

        n1.invoke(n2.invoke(""));
        return super.hashCode();
    }

    private boolean contains(ArrayList<Nested> nList, Nested n) {
        return nList.contains(n);
    }
}
