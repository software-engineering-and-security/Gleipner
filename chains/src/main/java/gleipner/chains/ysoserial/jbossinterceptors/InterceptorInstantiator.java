package gleipner.chains.ysoserial.jbossinterceptors;

public interface InterceptorInstantiator<T,I> {

    T createFor(InterceptorReference<I> interceptorReference);

}
