package gleipner.chains.ysoserial.jbossinterceptors;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

public interface InvocationContext {

    public Object getTarget();

    public Object getTimer();

    public Method getMethod();


    public Constructor<?> getConstructor();

    public Object[] getParameters();


    public void setParameters(Object[] params);

    public Map<String, Object> getContextData();

    public Object proceed() throws Exception;
}
