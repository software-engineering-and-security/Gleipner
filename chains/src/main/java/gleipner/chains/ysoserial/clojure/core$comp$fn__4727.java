package gleipner.chains.ysoserial.clojure;

public class core$comp$fn__4727 implements IFn{

    Object function1;
    Object function2;

    public core$comp$fn__4727(Object f1, Object f2) {
        this.function1 = f1;
        this.function2 = f2;
    }

    public Object invoke(Object arg1) {
        IFn f1 = (IFn)this.function1;
        IFn f2 = (IFn)this.function2;
        Object a = arg1;
        arg1 = null;
        return f1.invoke(f2.invoke(a));
    }
}
