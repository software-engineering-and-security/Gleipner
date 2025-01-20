package gleipner.chains.ysoserial.jbossinterceptors;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterceptionModelImpl<T,I> implements InterceptionModel<T,I>{

    private final Map<InterceptionType, List<InterceptorMetadata<I>>> globalInterceptors = new HashMap<InterceptionType, List<InterceptorMetadata<I>>>();
    private final T interceptedEntity;

    public InterceptionModelImpl(T interceptedEntity) {
        this.interceptedEntity = interceptedEntity;
    }

    @Override
    public List<InterceptorMetadata<I>> getInterceptors(InterceptionType interceptionType, Method method) {
        if (interceptionType.isLifecycleCallback() && method != null) {
            throw new IllegalArgumentException("On a lifecycle callback, the associated method must be null");
        }

        if (!interceptionType.isLifecycleCallback() && method == null) {
            throw new IllegalArgumentException("Around-invoke and around-timeout interceptors are defined for a given method");
        }
        if (interceptionType.isLifecycleCallback()) {
            if (globalInterceptors.containsKey(interceptionType)) {
                return globalInterceptors.get(interceptionType);
            }
        } else {
              //  not reachable
            return null;
        }

        return Collections.EMPTY_LIST;

    }

}
