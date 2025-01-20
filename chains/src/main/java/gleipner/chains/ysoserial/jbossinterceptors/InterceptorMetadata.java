package gleipner.chains.ysoserial.jbossinterceptors;

public interface InterceptorMetadata<T> {

    boolean isEligible(InterceptionType interceptionType);
    InterceptorInvocation getInterceptorInvocation(Object interceptorInstance, InterceptionType interceptionType);
}
