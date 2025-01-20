package gleipner.chains.ysoserial.vaadin1;

import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.InterProcedural;

import java.io.Serializable;

public class Vaadin_Getter_SinkGadget implements Serializable {

    private String outputProperties;
    public SinkGadget sinkGadget;

    public Vaadin_Getter_SinkGadget(String outputProperties) {
        this.outputProperties = outputProperties;
    }


    @InterProcedural(InterProcedural.REFLECTIVE_METHOD_INVOKE)
    public String getOutputProperties() {
        sinkGadget.sinkMethod(outputProperties);
        return outputProperties;
    }

    public void setOutputProperties(String newValue) {
        this.outputProperties = newValue;
    }

}
