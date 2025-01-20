package gleipner.chains.taint;

import gleipner.chains.taint.nested.Nested;
import gleipner.chains.taint.nested.NestedSink;
import gleipner.chains.taint.nested.NestedString;
import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

public class Taint_009_FP_Trampoline extends GleipnerObject {

    public Nested nested;

    public Taint_009_FP_Trampoline() {
    }

    public Taint_009_FP_Trampoline(Nested nested) {
        this.nested = nested;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        String selector = nested.get(0).invoke("");
        NestedString command;

        switch (selector) {
            case "foo":
                command = new NestedString(nested.get(0).invoke(""));
                break;
            case "bar":
                command = new NestedString("baz");
                break;
            default:
                command = new NestedString("");
                break;
        }

        nested.get(1).invoke(command.invoke(""));

        return super.hashCode();
    }
}
