package gleipner.chains.ysoserial.jbossinterceptors;

import java.lang.reflect.Method;

public class DefaultInvocationContextFactory implements InvocationContextFactory{
    @Override
    public InvocationContext newInvocationContext(InterceptionChain chain, Object o, Method method, Object[] args) {
        return new InterceptorInvocationContext(chain, o, method, args);
    }

}
