package gleipner.chains.ysoserial.jrmplistener;

import gleipner.core.SinkGadget;

import java.util.LinkedList;

public class TCPTransport {

    private final LinkedList<TCPEndpoint> epList;

    public TCPTransport(LinkedList<TCPEndpoint> epList) {
        this.epList = epList;
    }

    public void exportObject(Target target) {

        TCPEndpoint ep = getEndpoint();
        String port = ep.getPort();

        new SinkGadget().sinkMethod(port);
    }

    private TCPEndpoint getEndpoint() {
        return epList.getLast();
    }

}
