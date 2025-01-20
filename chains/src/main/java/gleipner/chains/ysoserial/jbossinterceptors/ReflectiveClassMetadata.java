package gleipner.chains.ysoserial.jbossinterceptors;

import java.io.Serializable;

public class ReflectiveClassMetadata<T> implements  ClassMetadata<T>, Serializable {

    private final Class<T> clazz;
    private ReflectiveClassMetadata(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> ClassMetadata<T> of(Class<T> clazz) {
        return new ReflectiveClassMetadata<T>(clazz);
    }

    @Override
    public Iterable<MethodMetadata> getDeclaredMethods() {
        return null;
    }

    @Override
    public Class<T> getJavaClass() {
        return clazz;
    }

    @Override
    public String getClassName() {
        return clazz.getName();
    }

    @Override
    public ClassMetadata<?> getSuperclass() {
        Class<?> superClass = clazz.getSuperclass();
        return superClass == null ? null : new ReflectiveClassMetadata(superClass);
    }
}
