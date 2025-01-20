package gleipner.evaluator.eval;

import gleipner.core.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.List;

public interface EvaluationStrategy {

    public List<List<String>> read(File file) throws IOException;

    public EvaluationResult evaluate(List<String> chain) throws ClassNotFoundException, NoSuchMethodException;

    public static EvaluationResult evaluateSingle(Executable annotatedMethodOrCtor, EvaluationResult result) {
        for (Annotation annotation: annotatedMethodOrCtor.getDeclaredAnnotations() ) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationType.equals(FalsePositive.class)) {
                result.falsePositive = true;
            } else if (annotationType.equals(Ysoserial.class)) {
                result.isYsoserialChain = true;
            }else if (annotationType.equals(ChainLength.class)) {
                result.chainLength = annotatedMethodOrCtor.getAnnotation(ChainLength.class).value();
            } else if (annotationType.equals(InterProcedural.class)) {
                result.addInterProceduralChallenge(annotatedMethodOrCtor.getAnnotation(InterProcedural.class).value());
            } else if (annotationType.equals(IntraProcedurals.class)) {
                IntraProcedural[] intraProcedurals = annotatedMethodOrCtor.getAnnotation(IntraProcedurals.class).value();
                for (IntraProcedural a : intraProcedurals) {
                    result.addIntraProceduralChallenge(a.value());
                }
            }
        }
        return result;
    }

}
