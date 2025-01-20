package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.FalsePositive;

import java.util.EmptyStackException;
import java.util.Stack;

public class EmptyStackException_002_FP_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public int pop_cnt;

    public EmptyStackException_002_FP_Trampoline(int pop_cnt, SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.pop_cnt = pop_cnt;
    }

    @Override
    @FalsePositive
    public int hashCode() {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);

        try {
            for (int i = 0; i < pop_cnt; i++) {
                if (!stack.empty()) stack.pop();
            }
        } catch (EmptyStackException e) {
            sinkGadget.sinkMethod(taint);
        }


        return super.hashCode();
    }
}
