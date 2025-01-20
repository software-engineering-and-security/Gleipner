package gleipner.chains.ysoserial.jbossinterceptors;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleInterceptionChain implements InterceptionChain{

    private final Object target;

    private final Method targetMethod;
    private int currentPosition;
    private final List<InterceptorMethodInvocation> interceptorMethodInvocations;

    public SimpleInterceptionChain(Collection<InterceptorInvocation> interceptorInvocations, Object target, Method targetMethod) {
        this.target = target;
        this.targetMethod = targetMethod;
        this.currentPosition = 0;
        interceptorMethodInvocations = new ArrayList<InterceptorMethodInvocation>();
        for (InterceptorInvocation interceptorInvocation : interceptorInvocations) {
            interceptorMethodInvocations.addAll(interceptorInvocation.getInterceptorMethodInvocations());
        }
    }

    public Object invokeNextInterceptor(InvocationContext invocationContext) throws Throwable {

        if (hasNextInterceptor()) {

            int oldCurrentPosition = currentPosition;
            InterceptorMethodInvocation nextInterceptorMethodInvocation = interceptorMethodInvocations.get(currentPosition++);
            return nextInterceptorMethodInvocation.invoke(invocationContext);

        } else {
            if (targetMethod != null) {
                targetMethod.invoke(target);
            }
        }

        return null;

    }

    public boolean hasNextInterceptor() {
        return currentPosition < interceptorMethodInvocations.size();
    }

}
