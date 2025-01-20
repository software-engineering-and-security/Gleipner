package gleipner.chains.ysoserial.clojure;

import gleipner.core.SinkGadget;

public class main$eval_opt implements IFn{

    public SinkGadget sinkGadget;

    public main$eval_opt() {
    }

    public main$eval_opt(SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public Object invoke(Object arg1) {
        Object invokeArg = arg1;
        arg1 = null;
        return invokeStatic(invokeArg);
    }

    public Object invokeStatic(Object invokeArg) {
        this.sinkGadget.sinkMethod(invokeArg.toString());
        return 10;
    }
}
