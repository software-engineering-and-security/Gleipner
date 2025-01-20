package instrumenter.sinks;
import java.lang.reflect.*;

public class Sinks {
    public static Sinks sinks = new Sinks();
    public static String methodName1 = "hashCode";
    public static String methodName2 = "toString";
    public static String methodName3 = "sinkMethod";
    public static String className1 = "instrumenter.sinks.Sinks";

    public static Method getMethod() throws Exception {
        return Sinks.class.getDeclaredMethod("sinkMethod", null);
    }
    public void sinkMethod() {
        System.err.println("sinkMethod invoked.");
    }

}
