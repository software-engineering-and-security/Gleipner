package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.core.GleipnerObject;

public class Taint_011_Trampoline extends GleipnerObject {

    public Nested n1;
    public Nested n2;

    public Taint_011_Trampoline() {
    }

    public Taint_011_Trampoline(Nested n1, Nested n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public int hashCode() {

        Nested sink = n1.get(0);
        Nested taint = n2.get(0);

        // see PayloadTest, the nested can reference themselves, making the loop depth rather irrelevant
        for(int i = 0; i < 100; i++) {
            sink = sink.get(0);
        }

        for( int i = 0; i < 100; i++) {
            taint = taint.get(0);
        }

        if (n1.get(2).invoke("").equals(n2.get(2).invoke(""))) {
            sink.invoke(taint.invoke(""));
        }

        return super.hashCode();
    }
}
