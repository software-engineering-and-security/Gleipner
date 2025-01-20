package gleipner.chains.ysoserial.cc1;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class AnnotationInvocationHandler implements InvocationHandler, Serializable {

    private final Class<? extends Annotation> type;
    public final IntermediaryMap<String, Object> memberValues;

    protected AnnotationInvocationHandler(Class<? extends Annotation> type, IntermediaryMap<String, Object> memberValues) {
        this.type = type;
        this.memberValues = memberValues;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String member = method.getName();
        Class<?>[] paramTypes = method.getParameterTypes();

        // Handle Object and Annotation methods
        if (member.equals("equals") && paramTypes.length == 1 &&
                paramTypes[0] == Object.class)
            return false;
        assert paramTypes.length == 0;
        if (member.equals("toString"))
            return "@annotation(mocked=mocked)";
        if (member.equals("hashCode"))
            return 42;
        if (member.equals("annotationType"))
            return type;

        // Handle annotation member accessors
        Object result = memberValues.get(member);
        return result;
    }


    private void readObject(java.io.ObjectInputStream s) throws Exception {
        s.defaultReadObject();
        // Check to make sure that types have not evolved incompatibly
        // ...
        try {
        memberValues.entrySet();
        } catch (ClassCastException e) {}

        //for (Map.Entry<String, Object> memberValue : memberValues.entrySet()) {
            // this does not matter ...
        //}

    }


}
