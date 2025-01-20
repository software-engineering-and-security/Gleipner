package gleipner.chains.ysoserial.jrmplistener;

import gleipner.core.SinkGadget;

import java.util.LinkedList;

public class TCPEndpoint implements Endpoint{

    private TCPTransport transport;
    private String port;
    private SinkGadget sinkGadget;

    public static TCPEndpoint getLocalEndpoint(String port, SinkGadget sinkGadget) {
        TCPEndpoint ep = new TCPEndpoint();
        ep.port = port;
        ep.sinkGadget = sinkGadget;
        LinkedList<TCPEndpoint> epList = new LinkedList<TCPEndpoint>();
        epList.add(ep);
        ep.transport = new TCPTransport(epList);

        return ep;
    }

    public void exportObject(Target target) {
        transport.exportObject(target);
    }

    public String getPort() {
        return this.port;
    }


}
