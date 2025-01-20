package gleipner.chains.ysoserial.cc1;

import gleipner.core.SinkGadget;
import gleipner.core.annotations.Ysoserial;

import java.io.Serializable;

public class Sink implements Serializable {

    @Ysoserial
    public void invokeSink() {
        new SinkGadget().sinkMethod("cc");
    }

}
