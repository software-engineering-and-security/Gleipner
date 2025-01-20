package gleipner.chains.ysoserial.jbossinterceptors;

public class ClassMetadataInterceptorReference implements InterceptorReference<ClassMetadata<?>>{

    private final ClassMetadata<?> classMetadata;

    private ClassMetadataInterceptorReference(ClassMetadata<?> classMetadata) {
        this.classMetadata = classMetadata;
    }

    public static InterceptorReference<ClassMetadata<?>> of(ClassMetadata<?> classMetadata) {
        return new ClassMetadataInterceptorReference(classMetadata);
    }
    @Override
    public ClassMetadata<?> getInterceptor() {
        return null;
    }
}
