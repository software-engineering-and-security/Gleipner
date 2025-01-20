package gleipner.chains.ysoserial.groovy;

import gleipner.core.SinkGadget;

import java.util.ArrayList;
import java.util.List;

public class MetaClassImpl implements MetaClass{

    protected final Class theClass;
    private final List<MetaMethod> allMethods = new ArrayList<MetaMethod>();
    private static final String CLOSURE_CALL_METHOD = "call";
    private static final String CLOSURE_DO_CALL_METHOD = "doCall";
    public static final Object[] EMPTY_ARRAY = {};
    public static final Object[] EMPTY_ARGUMENTS = {};

    public MetaClassImpl(final Class theClass){
        this.theClass = theClass;
    }



    public Object invokeMethod(Object object, String methodName, Object arguments) {
        if (arguments == null) {
            return invokeMethod(object, methodName, EMPTY_ARRAY);
        }

        if (arguments instanceof Object[]) {
            return invokeMethod(object, methodName, (Object[]) arguments);
        } else {
            return invokeMethod(object, methodName, new Object[]{arguments});
        }
    }

    public Object invokeMethod(Object object, String methodName, Object[] originalArguments) {
        return invokeMethod(theClass, object, methodName, originalArguments, false, false);
    }


    public Object invokeMethod(Class sender, Object object, String methodName, Object[] originalArguments, boolean isCallToSuper, boolean fromInsideClass) {

        // Object and methodName are tainted
        final Object[] arguments = originalArguments == null ? EMPTY_ARGUMENTS : originalArguments;
        MetaMethod method = null;

        if (method == null) {
            method = getMethodWithoutCaching(sender, methodName, arguments, isCallToSuper);
        }

        final boolean isClosure = object instanceof Closure;


        if (isClosure) {
            final Closure closure = (Closure) object;
            final Object owner = closure.getOwner();

            if (CLOSURE_CALL_METHOD.equals(methodName) || CLOSURE_DO_CALL_METHOD.equals(methodName)) {
                final Class objectClass = object.getClass();
                if (objectClass == MethodClosure.class) {
                    final MethodClosure mc = (MethodClosure) object;
                    methodName = mc.getMethod();
                    final Class ownerClass = owner instanceof Class ? (Class) owner : owner.getClass();
                    final MetaClass ownerMetaClass = new MetaClassImpl(ownerClass);
                    return ownerMetaClass.invokeMethod(ownerClass, owner, methodName, arguments, false, false);
                }
            }
        }

        if (method != null) {
            return method.doMethodInvoke(object, arguments);
        }
        return null;
    }

    public MetaMethod getMethodWithoutCaching(Class sender, String methodName, Object[] arguments, boolean isCallToSuper) {
        MetaMethod method = null;
        Object methods = getMethods(sender, methodName, isCallToSuper);
        if (methods != null) {
            method = (MetaMethod) chooseMethod(methodName, methods, arguments);
        }
        return method;
    }

    private Object chooseMethod(String methodName, Object methods, Object[] arguments) {
        if (methods instanceof MetaMethod) {
            return (MetaMethod) methods;
        }
        return null;
    }

    private Object getMethods(Class sender, String methodName, boolean isCallToSuper) {
        return MetaMethodIndex.getMethods(sender, methodName);
    }


}
