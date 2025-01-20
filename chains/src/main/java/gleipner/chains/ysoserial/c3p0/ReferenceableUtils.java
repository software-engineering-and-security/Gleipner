package gleipner.chains.ysoserial.c3p0;

import gleipner.core.SinkGadget;

import javax.naming.Reference;

public class ReferenceableUtils {

    public static SinkGadget sinkGadget;
    public static void referenceToObject(Reference reference) {
        String className = reference.getFactoryClassName();
        String classLocation = reference.getFactoryClassLocation();

        ReferenceableUtils.sinkGadget.sinkMethod(className);
    }
}
