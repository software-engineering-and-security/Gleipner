package gleipner.chains.ysoserial.jrmplistener;

import gleipner.core.SinkGadget;

import java.io.Serializable;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

public class UnicastRemoteObject implements Serializable, Remote {

    RMIClientSocketFactory csf = null;
    RMIServerSocketFactory ssf = null;
    private String port = "0";
    private SinkGadget sinkGadget;


    protected UnicastRemoteObject() {
        this("0");
    }

    protected UnicastRemoteObject(String port) {
        this.port = port;
    }

    protected UnicastRemoteObject(String port, SinkGadget sinkGadget) {
        this.port = port;
        this.sinkGadget = sinkGadget;
    }

    private void readObject(java.io.ObjectInputStream in)
            throws java.io.IOException, java.lang.ClassNotFoundException
    {
        in.defaultReadObject();
        reexport();
    }

    private void reexport() {
        if (csf == null && ssf == null) {
            exportObject((Remote) this, port, sinkGadget);
        }
    }

    public static Remote exportObject(Remote obj, String port, SinkGadget sinkGadget) {
        return exportObject(obj, new UnicastServerRef(port, sinkGadget));
    }

    public static Remote exportObject(Remote obj, UnicastServerRef sref) {
        return sref.exportObject(obj, null, false);
    }

}
