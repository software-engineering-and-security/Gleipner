package gleipner.chains.ysoserial.groovy;

public abstract class GroovyObjectSupport {

    private transient MetaClass metaClass = getDefaultMetaClass();

    public GroovyObjectSupport() {
        // remove InvokerHelper.getMetaClass to mock groovy system
        this.metaClass = InvokerHelper.getMetaClass(this.getClass());
    }

    public MetaClass getDefaultMetaClass() {
            return InvokerHelper.getMetaClass(this.getClass());
    }

    public MetaClass getMetaClass() {
        return this.metaClass;
    }




}
