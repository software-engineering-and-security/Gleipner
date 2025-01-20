package gleipner.chains.ysoserial.jbossinterceptors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

public class InterceptorMethodHandler implements Serializable {

    private static final MethodHandler DEFAULT_METHOD_HANDLER = new MethodHandler() {
        public Object invoke(Object self, Method m,
                             Method proceed, Object[] args)
                throws Exception {
            return proceed.invoke(self, args);
        }
    };

    private final Object targetInstance;
    private InterceptionModel<ClassMetadata<?>, ?> interceptionModel;
    private final Map<InterceptorMetadata<?>, Object> interceptorHandlerInstances = new HashMap<InterceptorMetadata<?>, Object>();
    private InterceptorMetadata<ClassMetadata<?>> targetClassInterceptorMetadata;
    private final InvocationContextFactory invocationContextFactory;

    public InterceptorMethodHandler(Object targetInstance,
                                    ClassMetadata<?> targetClassMetadata,
                                    InterceptionModel<ClassMetadata<?>, ?> interceptionModel,
                                    InterceptorInstantiator<?, ?> interceptorInstantiator,
                                    InvocationContextFactory invocationContextFactory) {
        this.targetInstance = targetInstance;
        this.invocationContextFactory = invocationContextFactory;
        if (interceptionModel == null) {
            throw new IllegalArgumentException("Interception model must not be null");
        }
        if (interceptorInstantiator == null) {
            throw new IllegalArgumentException("Interception handler factory must not be null");
        }
        this.interceptionModel = interceptionModel;
        /*
        for (InterceptorMetadata interceptorMetadata : this.interceptionModel.getAllInterceptors()) {
            interceptorHandlerInstances.put(interceptorMetadata, interceptorInstantiator.createFor(interceptorMetadata.getInterceptorReference()));
        }

         */
        targetClassInterceptorMetadata = InterceptorMetadataUtils.readMetadataForTargetClass(targetClassMetadata);
    }


    protected boolean isProxy() {
        return targetInstance != null;
    }

    private Object executeInterception(Object self, Method proceedingMethod, Method thisMethod, Object[] args, InterceptionType interceptionType) throws Throwable {

        // since thisMethod == null and interceptionType isLifecycleCallback -> interceptionModel.getInterceptors returns EMPTY_LIST
        List<? extends InterceptorMetadata> interceptorList = interceptionModel.getInterceptors(interceptionType, thisMethod);
        Collection<InterceptorInvocation> interceptorInvocations = new ArrayList<InterceptorInvocation>();

        for (InterceptorMetadata interceptorReference : interceptorList) {
            //interceptorInvocations.add(interceptorReference.getInterceptorInvocation(interceptorHandlerInstances.get(interceptorReference), interceptorReference, interceptionType));
        }

        if (targetClassInterceptorMetadata != null && targetClassInterceptorMetadata.isEligible(interceptionType)) {
            //interceptorInvocations.add(targetClassInterceptorMetadata.getInterceptorInvocation(isProxy() ? targetInstance : self, targetClassInterceptorMetadata, interceptionType));
        }
        SimpleInterceptionChain chain = new SimpleInterceptionChain(interceptorInvocations, isProxy() ? targetInstance : self, isProxy() ? thisMethod : proceedingMethod);
        return chain.invokeNextInterceptor(invocationContextFactory.newInvocationContext(chain, isProxy() ? targetInstance : self, isProxy() ? thisMethod : proceedingMethod, args));
    }


    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        try {
            objectInputStream.defaultReadObject();
            if (isProxy() && targetInstance instanceof ProxyObject && ((ProxyObject) targetInstance).getHandler() == null) {
                ((ProxyObject) targetInstance).setHandler(DEFAULT_METHOD_HANDLER);
            }
            executeInterception(isProxy() ? targetInstance : null, null, null, null, InterceptionType.POST_ACTIVATE);
        } catch (Throwable throwable) {
            throw new IOException("Error while deserializing class", throwable);
        }
    }

}
