package gleipner.chains.ysoserial.jbossinterceptors;

import java.util.Collection;

public interface InterceptorInvocation {

    Collection<InterceptorMethodInvocation> getInterceptorMethodInvocations();
}
