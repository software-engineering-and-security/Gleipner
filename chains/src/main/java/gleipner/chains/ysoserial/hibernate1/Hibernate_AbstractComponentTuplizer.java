package gleipner.chains.ysoserial.hibernate1;

import gleipner.core.annotations.InterProcedural;

public class Hibernate_AbstractComponentTuplizer implements Hibernate_ComponentTuplizer{

    public Hibernate_Getter[] getters;
    protected final int propertySpan;

    public Hibernate_AbstractComponentTuplizer(int propertySpan) {
        this.propertySpan = propertySpan;
        getters = new Hibernate_Getter[propertySpan];
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public Object getPropertyValue(Object component, int i) {
        return getters[i].get( component );
    }
}
