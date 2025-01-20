package gleipner.chains.ysoserial.rome;

import java.io.Serializable;

public class Rome_EqualsBean implements Serializable {

    private Class _beanClass;
    private Object _obj;

    public Rome_EqualsBean(Class beanClass, Object obj) {
        if (!beanClass.isInstance(obj)) {
            throw new IllegalArgumentException(obj.getClass() + " is not instance of " + beanClass);
        } else {
            this._beanClass = beanClass;
            this._obj = obj;
        }
    }

    public int beanHashCode() { return this._obj.toString().hashCode();}

}
