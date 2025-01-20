package gleipner.chains.ysoserial.spring;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public abstract class SerializableTypeWrapper {

    static interface TypeProvider extends Serializable {
        Type getType();
        Object getSource();
    }


    public static class MethodInvokeTypeProvider implements TypeProvider{

        private final TypeProvider provider;
        private final String methodName;
        private transient Object result;

        public MethodInvokeTypeProvider(TypeProvider provider, Method method) {
            this.provider = provider;
            this.methodName = method.getName();
            this.result = ReflectionUtils.invokeMethod(method, provider.getType());
        }

        @Override
        public Type getType() {
            return null;
        }

        @Override
        public Object getSource() {
            return null;
        }

        private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
            inputStream.defaultReadObject();

            Method method = ReflectionUtils.findMethod(this.provider.getType().getClass(), this.methodName);
            this.result = ReflectionUtils.invokeMethod(method, this.provider.getType());
        }
    }
}
