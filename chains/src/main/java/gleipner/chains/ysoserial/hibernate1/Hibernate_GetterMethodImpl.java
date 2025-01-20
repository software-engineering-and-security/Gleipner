package gleipner.chains.ysoserial.hibernate1;

import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.Ysoserial;

public class Hibernate_GetterMethodImpl implements Hibernate_Getter{

    public SinkGadget sinkGadget;

    public Hibernate_GetterMethodImpl() {

    }

    public Hibernate_GetterMethodImpl(SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    @Ysoserial
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public Object get(Object owner) {
        this.sinkGadget.sinkMethod(owner.toString());
        return null;
    }

    @Override
    public Class getReturnType() {
        return null;
    }
}
