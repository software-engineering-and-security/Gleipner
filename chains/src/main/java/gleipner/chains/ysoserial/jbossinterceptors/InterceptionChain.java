package gleipner.chains.ysoserial.jbossinterceptors;

public interface InterceptionChain {
    Object invokeNextInterceptor(InvocationContext invocationContext) throws Throwable;
    boolean hasNextInterceptor();
}
