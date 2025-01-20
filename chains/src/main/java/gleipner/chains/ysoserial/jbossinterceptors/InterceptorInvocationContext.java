package gleipner.chains.ysoserial.jbossinterceptors;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

public class InterceptorInvocationContext implements InvocationContext{

    private final Method method;

    private Object[] parameters;

    private final Object target;

    private final InterceptionChain interceptionChain;

    private final Object timer;

    public InterceptorInvocationContext(InterceptionChain interceptionChain, Object target, Method targetMethod, Object[] parameters) {
        this.interceptionChain = interceptionChain;
        this.method = targetMethod;
        this.parameters = parameters;
        this.target = target;
        this.timer = null;
    }

    @Override
    public Object getTarget() {
        return null;
    }

    @Override
    public Object getTimer() {
        return null;
    }

    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public Constructor<?> getConstructor() {
        return null;
    }

    @Override
    public Object[] getParameters() {
        return new Object[0];
    }

    @Override
    public void setParameters(Object[] params) {

    }

    @Override
    public Map<String, Object> getContextData() {
        return null;
    }

    @Override
    public Object proceed() throws Exception {
        return null;
    }
}
