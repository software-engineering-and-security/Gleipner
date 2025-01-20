package gleipner.chains.ysoserial.hibernate1;

import java.io.Serializable;

public interface Hibernate_Getter extends Serializable {

    Object get(Object owner);
    Class getReturnType();
}
