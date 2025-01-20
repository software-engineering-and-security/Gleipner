package gleipner.chains.ysoserial.jbossinterceptors;

import java.util.Collection;

public class SimpleMethodInvocation<T> implements InterceptorMethodInvocation{

    final T instance;
    final MethodMetadata method;
    private boolean targetClass;
    private InterceptionType interceptionType;

    SimpleMethodInvocation(T instance, MethodMetadata method, boolean targetClass, InterceptionType interceptionType) {
        this.instance = instance;
        this.method = method;
        this.targetClass = targetClass;
        this.interceptionType = interceptionType;
    }

    @Override
    public Object invoke(InvocationContext invocationContext) throws Exception {
        if (invocationContext != null)
            return method.getJavaMethod().invoke(instance, invocationContext);
        else
            return method.getJavaMethod().invoke(instance);
    }

    @Override
    public boolean expectsInvocationContext() {
        return false;
    }
}
