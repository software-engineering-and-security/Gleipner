package gleipner.chains.ysoserial.jrmplistener;

import gleipner.core.SinkGadget;

public class UnicastServerRef extends UnicastRef{

    public UnicastServerRef(String port, SinkGadget sinkGadget) {
        super(new LiveRef(port, sinkGadget));
    }

    public Remote exportObject(Remote impl, Object data,  boolean permanent) {
        Class<?> implClass = impl.getClass();
        Remote stub;

        // target doesnt matter, the taint being propagated is the port ...
        Target target = new Target();
        ref.exportObject(target);

        return null;
    }

}
