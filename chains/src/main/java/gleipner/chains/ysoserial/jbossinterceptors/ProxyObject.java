package gleipner.chains.ysoserial.jbossinterceptors;

public interface ProxyObject {

    void setHandler(MethodHandler mi);

    MethodHandler getHandler();
}
