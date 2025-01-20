package gleipner.chains.ysoserial.spring;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class AutowireUtils {

    private static class ObjectFactoryDelegatingInvocationHandler implements InvocationHandler, Serializable {


        private final ObjectFactory<?> objectFactory;

        public ObjectFactoryDelegatingInvocationHandler(ObjectFactory<?> objectFactory) {
            this.objectFactory = objectFactory;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            System.out.println(methodName);


            if (methodName.equals("equals")) {
                // Only consider equal when proxies are identical.
                return (proxy == args[0]);
            }
            else if (methodName.equals("hashCode")) {
                // Use hashCode of proxy.
                return System.identityHashCode(proxy);
            }
            else if (methodName.equals("toString")) {
                return this.objectFactory.toString();
            }
            try {
                return method.invoke(this.objectFactory.getObject(), args);
            }
            catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
        }
    }


}
