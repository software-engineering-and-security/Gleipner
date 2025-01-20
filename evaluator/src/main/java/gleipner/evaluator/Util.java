package gleipner.evaluator;

import java.lang.reflect.Array;

public class Util {

    public static Class<?> signatureToType(String signature) throws ClassNotFoundException {

        Class type = null;
        char identifier = signature.charAt(0);
        boolean isArrayType = false;

        if (identifier == '[') {
            isArrayType = true;
            identifier = signature.charAt(1);
        }


        switch (identifier) {
            case 'L':
                String classSignature;
                if (isArrayType) {
                    classSignature = signature.substring(2).replaceAll("/", ".");
                } else {
                    classSignature = signature.substring(1).replaceAll("/", ".");
                }
                type = Class.forName(classSignature);
                break;
            case 'I':
                type = int.class;
                break;
            case 'Z':
                type = boolean.class;
                break;
            case 'F':
                type = float.class;
                break;
            default:
                break;
        }

        if (isArrayType) {
            return Array.newInstance(type, 0).getClass();
        }

        return type;
    }


}
