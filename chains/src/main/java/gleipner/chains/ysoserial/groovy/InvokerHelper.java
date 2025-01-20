package gleipner.chains.ysoserial.groovy;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.SinkAccessPaths;
import gleipner.core.annotations.Ysoserial;

public class InvokerHelper {

    public static MetaClass getMetaClass(Class clazz) {
        // mocked metaregistry
        return new MetaClassImpl(clazz);
    }

}
