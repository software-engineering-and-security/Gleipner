package gleipner.chains.ysoserial.jrmplistener;

import gleipner.core.SinkGadget;

public class LiveRef {


    private final Endpoint ep;

    public LiveRef(String port, SinkGadget sinkGadget) {
        ep = TCPEndpoint.getLocalEndpoint(port, sinkGadget);
    }

    public void exportObject(Target target) {
        ep.exportObject(target);
    }

}
