package gleipner.chains.ysoserial.rome;

import gleipner.core.GleipnerObject;

import java.util.Set;

public class Rome_ObjectBean extends GleipnerObject {

    private Rome_EqualsBean _equalsBean;
    private Rome_ToStringBean _toStringBean;

    public Rome_ObjectBean(Class beanClass, Object obj) {
        this(beanClass, obj, (Set) null);
    }

    public Rome_ObjectBean(Class beanClass, Object obj, Set ignoreProperties) {
        this._equalsBean = new Rome_EqualsBean(beanClass, obj);
        this._toStringBean = new Rome_ToStringBean(beanClass, obj);
    }

    @Override
    public int hashCode() {
        return this._equalsBean.beanHashCode();
    }

    public String toString() {
        return this._toStringBean.toString();
    }


}
