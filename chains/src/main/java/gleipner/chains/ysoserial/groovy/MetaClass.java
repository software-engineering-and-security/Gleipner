package gleipner.chains.ysoserial.groovy;

public interface MetaClass {
    Object invokeMethod(Class sender, Object receiver, String methodName, Object[] arguments, boolean isCallToSuper, boolean fromInsideClass);
    Object invokeMethod(Object object, String methodName, Object arguments);

}
