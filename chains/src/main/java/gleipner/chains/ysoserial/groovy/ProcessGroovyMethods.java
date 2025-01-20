package gleipner.chains.ysoserial.groovy;

import gleipner.core.SinkGadget;

public class ProcessGroovyMethods {

    public static SinkGadget sinkGadget;

    public static void sinkMethod(final String taint) {
        ProcessGroovyMethods.sinkGadget.sinkMethod(taint);
    }
}
