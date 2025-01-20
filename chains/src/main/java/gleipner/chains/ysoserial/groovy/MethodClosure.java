package gleipner.chains.ysoserial.groovy;

import gleipner.core.annotations.InterProcedural;

public class MethodClosure extends Closure{
    private String method;

    public MethodClosure(Object owner, String method) {
        super(owner);
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

}
