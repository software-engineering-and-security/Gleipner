package gleipner.chains.ysoserial.jbossinterceptors;

public interface InterceptorMethodInvocation {

        Object invoke(InvocationContext invocationContext) throws Exception;
        boolean expectsInvocationContext();


}
