package gleipner.chains.ysoserial.vaadin1;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.Ysoserial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Vaadin_NestedMethodProperty<T> extends Vaadin_AbstractProperty<T> {

    private Object instance;
    private transient List<Method> getMethods;
    private transient Method setMethod;
    private String propertyName;
    private Class<? extends T> type;

    static Method initGetterMethod(String propertyName, final Class<?> beanClass) throws NoSuchMethodException{

        Method getMethod = null;
        propertyName = Vaadin_StaticMethods.capitalize(propertyName);

        try {
            getMethod = beanClass.getMethod("get" + propertyName, new Class[] {});
        } catch (final NoSuchMethodException ignored) {
            try {
                getMethod = beanClass.getMethod("is" + propertyName,
                        new Class[] {});
            } catch (final NoSuchMethodException ignoredAsWell) {
                getMethod = beanClass.getMethod("are" + propertyName,
                        new Class[] {});
            }
        }
        return getMethod;

    }

    public Vaadin_NestedMethodProperty(Object instance, String propertyName) {
        this.instance = instance;
        initialize(instance.getClass(), propertyName);
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialize(instance.getClass(), propertyName);
    }


    private void initialize(Class<?> beanClass, String propertyName)
            throws IllegalArgumentException {

        List<Method> getMethods = new ArrayList<Method>();

        String lastSimplePropertyName = propertyName;
        Class<?> lastClass = beanClass;

        // first top-level property, then go deeper in a loop
        Class<?> propertyClass = beanClass;
        String[] simplePropertyNames = propertyName.split("\\.");
        if (propertyName.endsWith(".") || 0 == simplePropertyNames.length) {
            throw new IllegalArgumentException(
                    "Invalid property name '" + propertyName + "'");
        }
        for (String simplePropertyName : simplePropertyNames) {
            simplePropertyName = simplePropertyName.trim();
            if (!simplePropertyName.isEmpty()) {
                lastSimplePropertyName = simplePropertyName;
                lastClass = propertyClass;
                try {
                    Method getter = Vaadin_NestedMethodProperty.initGetterMethod(
                            simplePropertyName, propertyClass);
                    propertyClass = getter.getReturnType();
                    getMethods.add(getter);
                } catch (final NoSuchMethodException e) {
                    throw new IllegalArgumentException("Bean property '"
                            + simplePropertyName + "' not found", e);
                }
            } else {
                throw new IllegalArgumentException(
                        "Empty or invalid bean property identifier in '"
                                + propertyName + "'");
            }
        }

        Method lastGetMethod = getMethods.get(getMethods.size() - 1);
        Class<?> type = lastGetMethod.getReturnType();

        // Finds the set method
        Method setMethod = null;
        try {
            // Assure that the first letter is upper cased (it is a common
            // mistake to write firstName, not FirstName).
            lastSimplePropertyName = Vaadin_StaticMethods
                    .capitalize(lastSimplePropertyName);

            setMethod = lastClass.getMethod("set" + lastSimplePropertyName,
                    new Class[]{type});
        } catch (final NoSuchMethodException skipped) {
        }

        this.type = (Class<? extends T>) Vaadin_StaticMethods.convertPrimitiveType(type);
        this.propertyName = propertyName;
        this.getMethods = getMethods;
        this.setMethod = setMethod;
    }



    public boolean isReadOnly() {
        return super.isReadOnly() || (null == setMethod);
    }


    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @Ysoserial
    @Override
    public T getValue() {
        try {
            Object object = instance;
            for (Method m : getMethods) {
                object = m.invoke(object);
                if (object == null) {
                    return null;
                }
            }
            return (T) object;
        } catch (final Throwable e) {
           e.printStackTrace();
           return null;
        }
    }
}
