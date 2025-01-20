package gleipner.chains.ysoserial.hibernate1;


import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.IntraProcedural;

public class Hibernate_ComponentType extends Hibernate_AbstractType {

    protected final int propertySpan;
    private final Hibernate_Type[] propertyTypes;
    protected final Hibernate_ComponentTuplizer componentTuplizer;

    public Hibernate_ComponentType(int propertySpan, Hibernate_Type[] propertyTypes, Hibernate_ComponentTuplizer tuplizer) {
        // we can mock this, since the relevant fields will be set via Reflection and then serialized
        this.propertySpan =propertySpan;
        this.propertyTypes = propertyTypes;
        this.componentTuplizer = tuplizer;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @IntraProcedural(IntraProcedural.REFLECTION)
    @IntraProcedural(IntraProcedural.CONTROL_FLOW)
    public Object getPropertyValue(Object component, int i){
        if ( component instanceof Object[] ) {
            return ( (Object[]) component )[i];
        }
        else {
            return componentTuplizer.getPropertyValue( component, i );
        }
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @IntraProcedural(IntraProcedural.CONTROL_FLOW)
    public int getHashCode(final Object x) {
        int result = 17;
        for ( int i = 0; i < propertySpan; i++ ) {
            Object y = getPropertyValue( x, i );
            result *= 37;
            if ( y != null ) {
                result += propertyTypes[i].getHashCode( y );
            }
        }
        return result;
    }


}
