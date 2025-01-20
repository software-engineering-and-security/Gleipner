package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.chains.taint.nested.NestedString;
import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

public class Taint_014_FP_Trampoline extends GleipnerObject {

    public Nested nested;

    public Taint_014_FP_Trampoline() {
    }

    public Taint_014_FP_Trampoline(Nested nested) {
        this.nested = nested;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Nested tree = buildTree(this.nested);

        try {
            recursiveSearch(tree, "foo");
            this.nested.get(1).invoke(this.nested.get(2).invoke(""));
        } catch (Exception e) {

        }
        return super.hashCode();
    }

    public Nested buildTree(Nested n) {
        Nested root = new Nested(1);
        Nested child = new Nested(1);
        root.set(0, child);
        child.set(0, new NestedString("bar"));
        child.set(1, n);
        return root;
    }


    public NestedString recursiveSearch(Nested n, String val) throws ArrayIndexOutOfBoundsException{
        if (n.invoke("") != null && n.invoke("").equals(val)) return (NestedString) n;
        return recursiveSearch(n.get(0), val);
    }
}
