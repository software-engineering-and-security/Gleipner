package gleipner.chains.ysoserial.jbossinterceptors;

import java.lang.reflect.Method;
import java.util.Set;

public interface MethodMetadata {
        Method getJavaMethod();
        Set<InterceptionType> getSupportedInterceptionTypes();
        Class<?> getReturnType();

}
