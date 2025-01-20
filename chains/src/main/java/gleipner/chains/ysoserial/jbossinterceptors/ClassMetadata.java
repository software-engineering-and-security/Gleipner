package gleipner.chains.ysoserial.jbossinterceptors;

import java.io.Serializable;

public interface ClassMetadata<T> extends Serializable {
    Iterable<MethodMetadata> getDeclaredMethods();

    Class<T> getJavaClass();

    String getClassName();

    ClassMetadata<?> getSuperclass();

}
