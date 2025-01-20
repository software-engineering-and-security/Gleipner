package gleipner.chains.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy_003_FP_InvocationHandler implements InvocationHandler {

    private String taint;

    public Proxy_003_FP_InvocationHandler(String taint) {
        this.taint = taint;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("foo")) {
            return taint;
        }

        return "";
    }
}
