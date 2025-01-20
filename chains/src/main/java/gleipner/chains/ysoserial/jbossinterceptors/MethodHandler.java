package gleipner.chains.ysoserial.jbossinterceptors;

import java.lang.reflect.Method;

public interface MethodHandler {

    Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable;

}
