package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.util.EmptyStackException;
import java.util.Stack;

public class EmptyStackException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public int pop_cnt;

    public EmptyStackException_001_Trampoline(int pop_cnt, SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.pop_cnt = pop_cnt;
    }

    @Override
    public int hashCode() {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);

        try {
            for (int i = 0; i < pop_cnt; i++) {
                stack.pop();
            }
        } catch (EmptyStackException e) {
            sinkGadget.sinkMethod(taint);
        }


        return super.hashCode();
    }
}
