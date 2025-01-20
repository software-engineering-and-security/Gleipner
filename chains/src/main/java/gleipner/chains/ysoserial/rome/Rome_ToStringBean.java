package gleipner.chains.ysoserial.rome;

import gleipner.core.annotations.Ysoserial;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Stack;

public class Rome_ToStringBean implements Serializable {



    private static final ThreadLocal PREFIX_TL = new ThreadLocal() {
        public Object get() {
            Object o = super.get();
            if (o == null) {
                o = new Stack();
                this.set(o);
            }

            return o;
        }
    };

    private static final Object[] NO_PARAMS = new Object[0];
    private Class _beanClass;
    private Object _obj;

    public Rome_ToStringBean(Class beanClass, Object obj) {
        this._beanClass = beanClass;
        this._obj = obj;
    }

    public String toString() {
        Stack stack = (Stack) PREFIX_TL.get();
        String[] tsInfo = (String[])(stack.isEmpty() ? null : stack.peek());
        String prefix;
        if (tsInfo == null) {
            String className = this._obj.getClass().getName();
            prefix = className.substring(className.lastIndexOf(".") + 1);
        } else {
            prefix = tsInfo[0];
            tsInfo[1] = prefix;
        }

        return this.toString(prefix);
    }

    @Ysoserial
    private String toString(String prefix) {
        StringBuffer sb = new StringBuffer(128);

        try {
            PropertyDescriptor[] pds = Rome_BeanIntrospector.getPropertyDescriptors(this._beanClass);
            if (pds != null) {
                for(int i = 0; i < pds.length; ++i) {
                    String pName = pds[i].getName();
                    Method pReadMethod = pds[i].getReadMethod();
                    if (pReadMethod != null && pReadMethod.getDeclaringClass() != Object.class && pReadMethod.getParameterTypes().length == 0) {
                        Object value = pReadMethod.invoke(this._obj, NO_PARAMS);
                        //this.printProperty(sb, prefix + "." + pName, value);
                    }
                }
            }
        } catch (Exception var8) {
            sb.append("\n\nEXCEPTION: Could not complete " + this._obj.getClass() + ".toString(): " + var8.getMessage() + "\n");
        }

        return sb.toString();
    }


}
