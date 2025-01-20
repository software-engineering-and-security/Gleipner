package gleipner.chains.ysoserial.jbossinterceptors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SimpleInterceptorMetadata<T> implements InterceptorMetadata<T>, Serializable {

    private final Map<InterceptionType, List<MethodMetadata>> interceptorMethodMap;
    private final InterceptorReference<T> interceptorReference;
    private final boolean targetClass;

    public SimpleInterceptorMetadata(InterceptorReference<T> interceptorReference, boolean targetClass, Map<InterceptionType, List<MethodMetadata>> interceptorMethodMap) {
        this.interceptorReference = interceptorReference;
        this.targetClass = targetClass;
        this.interceptorMethodMap = interceptorMethodMap;
    }

    @Override
    public boolean isEligible(InterceptionType interceptionType) {
        if (this.interceptorMethodMap == null) {
            return false;
        }
        List<MethodMetadata> interceptorMethods = this.interceptorMethodMap.get(interceptionType);
        // return true if there are any interceptor methods for this interception type
        return interceptorMethods != null && interceptorMethods.isEmpty() == false;

    }

    public List<MethodMetadata> getInterceptorMethods(InterceptionType interceptionType) {
        if (interceptorMethodMap != null) {
            List<MethodMetadata> methods = interceptorMethodMap.get(interceptionType);
            return methods == null ? Collections.<MethodMetadata>emptyList() : methods;
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public InterceptorInvocation getInterceptorInvocation(Object interceptorInstance, InterceptionType interceptionType) {
        return new SimpleInterceptorInvocation(interceptorInstance, interceptionType, getInterceptorMethods(interceptionType), targetClass);
    }
}
