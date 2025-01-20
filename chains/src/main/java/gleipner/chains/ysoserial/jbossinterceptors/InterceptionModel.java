package gleipner.chains.ysoserial.jbossinterceptors;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

public interface InterceptionModel<T,I> extends Serializable {

    List<InterceptorMetadata<I>> getInterceptors(InterceptionType interceptionType, Method method);

}
