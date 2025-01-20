package gleipner.chains.ysoserial.vaadin1;

import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.IntraProcedural;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Vaadin_BadAttributeValueException extends Exception implements Serializable {

    private Object val;

    // No longer needed, since connected to TriggerGadget

    public Vaadin_BadAttributeValueException(String s) {
    }

    @InterProcedural(InterProcedural.READ_OBJECT)
    @IntraProcedural(IntraProcedural.REFLECTION)
    @IntraProcedural(IntraProcedural.CONTROL_FLOW)
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField gf = ois.readFields();
        Object valObj = gf.get("val", null);

        if (valObj == null) {
            val = null;
        } else if (valObj instanceof String) {
            val= valObj;
        } else if (System.getSecurityManager() == null
                || valObj instanceof Long
                || valObj instanceof Integer
                || valObj instanceof Float
                || valObj instanceof Double
                || valObj instanceof Byte
                || valObj instanceof Short
                || valObj instanceof Boolean) {
            val = valObj.hashCode();
        } else { // the serialized object is from a version without JDK-8019292 fix
            val = System.identityHashCode(valObj) + "@" + valObj.getClass().getName();
        }
    }
}
