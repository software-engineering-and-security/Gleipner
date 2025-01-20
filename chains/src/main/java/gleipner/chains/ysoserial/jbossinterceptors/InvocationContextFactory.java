package gleipner.chains.ysoserial.jbossinterceptors;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface InvocationContextFactory extends Serializable {
    InvocationContext newInvocationContext(InterceptionChain chain, Object o, Method method, Object[] args);

}
