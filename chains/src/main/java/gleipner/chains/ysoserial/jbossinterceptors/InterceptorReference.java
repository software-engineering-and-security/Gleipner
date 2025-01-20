package gleipner.chains.ysoserial.jbossinterceptors;

import java.io.Serializable;

public interface InterceptorReference<T> extends Serializable {
    T getInterceptor();
}
