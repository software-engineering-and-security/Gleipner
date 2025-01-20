package gleipner.core;

import gleipner.core.annotations.InterProcedural;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Controller {

    public static boolean isChainSuccess = false;
    public static final String TAINT_MARKER = "taint";

    public static Method getMethod(String className, String methodName) throws ClassNotFoundException {
       Method theMethod = null;

        for (Method method : Class.forName(className).getMethods()) {
            if (method.getName().equals(methodName)) {
                theMethod = method;
            }
        }

        if (theMethod == null) {
            for (Method method : Class.forName(className).getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    theMethod = method;
                }
            }
        }

        return theMethod;
    }

    public static void invokeSink(Object tainted, String taintedString) {
        try {
            Method m = tainted.getClass().getDeclaredMethod(taintedString);
            m.invoke(tainted);
        } catch (Exception e) {

        }
    }


    /**
     * This is a workaround for gadgetinspector to detect the sink method, since it requires a taint to propagated to this method, rather than just calling it.
     * @param taintedParams
     */
    @InterProcedural(InterProcedural.STATIC_INVOKE)
    public static void invokeSink(Object ... taintedParams) {

        boolean flagSuccess = true;

        for (Object taintedParam : taintedParams) {
            if (!taintedParam.toString().equals(TAINT_MARKER)) {
                flagSuccess = false;
            }
        }

        if (flagSuccess) Controller.isChainSuccess = true;

        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (StackTraceElement element: stackTrace) {
            if(element.getClassName().startsWith("gleipner")) {
                System.out.println(element.getClassName() + "." + element.getMethodName());

                try {
                    Method gadgetMethod = Controller.getMethod(element.getClassName(), element.getMethodName());

                    for (Annotation annotation: gadgetMethod.getDeclaredAnnotations()) {
                        System.out.println("  -> " + annotation);
                    }
                    for (Annotation annotation: gadgetMethod.getAnnotations()) {
                        System.out.println("  -> " + annotation);
                    }

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}
