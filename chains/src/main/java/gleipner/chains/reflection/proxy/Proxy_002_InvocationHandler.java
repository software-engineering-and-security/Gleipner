package gleipner.chains.reflection.proxy;

import gleipner.core.SinkGadget;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy_002_InvocationHandler implements InvocationHandler {

    private String taint;

    public Proxy_002_InvocationHandler(String taint) {
        this.taint = taint;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("invokeSink")) {
            return taint;
        }

        return "";
    }
}
