package gleipner.core.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(IntraProcedurals.class)
public @interface IntraProcedural {

    static final String REFLECTION = "reflection";
    static final String CONTROL_FLOW = "control flow";
    static final String TRY_CATCH_CLAUSE = "try catch clause";


    public String value();
}
