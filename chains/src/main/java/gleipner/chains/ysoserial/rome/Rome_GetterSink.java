package gleipner.chains.ysoserial.rome;

import gleipner.core.SinkGadget;

import java.io.Serializable;

public class Rome_GetterSink implements Serializable {
    private String sink;
    private SinkGadget sinkGadget;

    public Rome_GetterSink(String sink, SinkGadget sinkGadget) {
        this.sink = sink;
        this.sinkGadget = sinkGadget;
    }

    public String getSink()  {
        sinkGadget.sinkMethod(sink);
        return sink;
    }

}
