package gleipner.chains.ysoserial.jbossinterceptors;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleInterceptorInvocation<T> implements InterceptorInvocation{

    private Collection<InterceptorMethodInvocation> interceptorMethodInvocations;

    public SimpleInterceptorInvocation(T instance, InterceptionType interceptionType, Collection<MethodMetadata> interceptorMethods, boolean targetClass) {
        interceptorMethodInvocations = new ArrayList<InterceptorMethodInvocation>();
        for (MethodMetadata method : interceptorMethods) {
            interceptorMethodInvocations.add(new SimpleMethodInvocation(instance, method, targetClass, interceptionType));
        }
    }
    @Override
    public Collection<InterceptorMethodInvocation> getInterceptorMethodInvocations() {
        return interceptorMethodInvocations;
    }
}
