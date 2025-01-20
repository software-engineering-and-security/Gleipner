package gleipner.core.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface InterProcedural {

    public static final String STATIC_INVOKE = "invokestatic";
    public static final String VIRTUAL_INVOKE = "invokevirtual";
    public static final String PROXY = "proxy";
    public static final String CLINIT = "clinit";
    public static final String INIT = "init";
    public static final String REFLECTIVE_STATIC_INITIALIZER = "reflective static initializer";
    public static final String REFLECTIVE_CONSTRUCTOR = "reflective constructor call";
    public static final String DYNAMIC_INVOKE = "invokedynamic";
    public static final String REFLECTIVE_METHOD_INVOKE = "reflective method call";
    public static final String READ_OBJECT = "read object";
    public static final String JNI = "JNI";


    public String value();
}
