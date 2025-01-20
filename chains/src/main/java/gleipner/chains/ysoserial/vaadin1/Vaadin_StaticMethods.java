package gleipner.chains.ysoserial.vaadin1;

import java.util.Locale;

public class Vaadin_StaticMethods {

    public static Class<?> convertPrimitiveType(Class<?> type) {
        // Gets the return type from get method
        if (type.isPrimitive()) {
            if (type.equals(Boolean.TYPE)) {
                type = Boolean.class;
            } else if (type.equals(Integer.TYPE)) {
                type = Integer.class;
            } else if (type.equals(Float.TYPE)) {
                type = Float.class;
            } else if (type.equals(Double.TYPE)) {
                type = Double.class;
            } else if (type.equals(Byte.TYPE)) {
                type = Byte.class;
            } else if (type.equals(Character.TYPE)) {
                type = Character.class;
            } else if (type.equals(Short.TYPE)) {
                type = Short.class;
            } else if (type.equals(Long.TYPE)) {
                type = Long.class;
            }
        }
        return type;
    }

    public static String capitalize(String string) {
        if (string == null) {
            return null;
        }

        if (string.length() <= 1) {
            return string.toUpperCase(Locale.ROOT);
        }

        return string.substring(0, 1).toUpperCase(Locale.ROOT)
                + string.substring(1);
    }





}
