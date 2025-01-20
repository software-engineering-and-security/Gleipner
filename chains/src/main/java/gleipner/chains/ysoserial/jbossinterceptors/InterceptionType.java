package gleipner.chains.ysoserial.jbossinterceptors;

public enum InterceptionType {

    AROUND_INVOKE(false, "javax.interceptor.AroundInvoke"),
    AROUND_TIMEOUT(false, "javax.interceptor.AroundTimeout"),
    POST_CONSTRUCT(true, "javax.annotation.PostConstruct"),
    PRE_DESTROY(true, "javax.annotation.PreDestroy"),
    POST_ACTIVATE(true, "javax.ejb.PostActivate"),
    PRE_PASSIVATE(true, "javax.ejb.PrePassivate");

    private final boolean lifecycleCallback;
    private final String annotationClassName;

    InterceptionType(boolean lifecycleCallback, String annotationClassName) {
        this.lifecycleCallback = lifecycleCallback;
        this.annotationClassName = annotationClassName;
    }

    public boolean isLifecycleCallback() {
        return lifecycleCallback;
    }

    public String annotationClassName() {
        return annotationClassName;
    }

}
