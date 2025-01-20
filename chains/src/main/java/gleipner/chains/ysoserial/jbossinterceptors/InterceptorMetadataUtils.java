package gleipner.chains.ysoserial.jbossinterceptors;

import java.lang.reflect.Modifier;
import java.util.*;

public class InterceptorMetadataUtils {
    public static InterceptorMetadata readMetadataForTargetClass(ClassMetadata<?> classMetadata) {
        //return new SimpleInterceptorMetadata(ClassMetadataInterceptorReference.of(classMetadata), true, buildMethodMap(classMetadata, true));
        return null;
    }

    /*

    static Map<InterceptionType, List<MethodMetadata>> buildMethodMap(ClassMetadata<?> interceptorClass, boolean forTargetClass) {
        Map<InterceptionType, List<MethodMetadata>> methodMap = new HashMap<InterceptionType, List<MethodMetadata>>();
        ClassMetadata<?> currentClass = interceptorClass;
        Set<MethodReference> foundMethods = new HashSet<MethodReference>();
        do {
            Set<InterceptionType> detectedInterceptorTypes = new HashSet<InterceptionType>();

            for (MethodMetadata method : currentClass.getDeclaredMethods()) {
                MethodReference methodReference = MethodReference.of(method, Modifier.isPrivate(method.getJavaMethod().getModifiers()));
                if (!foundMethods.contains(methodReference)) {
                    for (InterceptionType interceptionType : InterceptionTypeRegistry.getSupportedInterceptionTypes()) {
                        if (isInterceptorMethod(interceptionType, method, forTargetClass)) {
                            if (methodMap.get(interceptionType) == null) {
                                methodMap.put(interceptionType, new LinkedList<MethodMetadata>());
                            }
                            if (detectedInterceptorTypes.contains(interceptionType)) {
                                throw new InterceptorMetadataException("Same interception type cannot be specified twice on the same class");
                            } else {
                                detectedInterceptorTypes.add(interceptionType);
                            }
                            // add method in the list - if it is there already, it means that it has been added by a subclass
                            // final methods are treated separately, as a final method cannot override another method nor be
                            // overridden
                            ReflectionUtils.ensureAccessible(method.getJavaMethod());
                            if (!foundMethods.contains(methodReference)) {
                                methodMap.get(interceptionType).add(0, method);
                            }
                        }
                    }
                    // the method reference must be added anyway - overridden methods are not taken into consideration
                    foundMethods.add(methodReference);
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        while (currentClass != null && !OBJECT_CLASS_NAME.equals(currentClass.getJavaClass()));
        return methodMap;
    }

     */

}
