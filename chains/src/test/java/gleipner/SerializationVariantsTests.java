package gleipner;

import gleipner.chains.serializationvariants.*;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializationVariantsTests {


    TriggerGadget triggerGadget;

    @Before
    public void before() {
        triggerGadget = new TriggerGadget();
        Controller.isChainSuccess = false;
    }

    @Test
    public void readExternal() {
        ReadExternal_TriggerGadget tg = new ReadExternal_TriggerGadget(new SinkGadget(), Controller.TAINT_MARKER);
        Serializer.externalize(tg);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void readResolve() {
        ReadResolve_TriggerGadget tg = new ReadResolve_TriggerGadget(new SinkGadget(), Controller.TAINT_MARKER);
        Serializer.serializeDeserialize(tg);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void readObject() {
        ReadObject_TriggerGadget tg = new ReadObject_TriggerGadget(new SinkGadget(), Controller.TAINT_MARKER);
        Serializer.serializeDeserialize(tg);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void readObjectNoData() {
        ReadObjectNoData_TriggerGadget tg = new ReadObjectNoData_TriggerGadget(new ReadObjectNoData_Child(), new SinkGadget(), Controller.TAINT_MARKER);

        // a bit contrived example for completeness
        // the readNoData.ser file was serialized without the ReadObjectNoData_Parent class existing
        // now when deserializing ReadObjectNoData_Child, the parent will be initialized through readObjectNoData, thus triggering the sink
        // in case readNoData.ser is lost: uncomment below and comment deserialization, then delete ReadObjectNoData_Parent temporarily, serialize, and bring Parent back

        //Serializer.serializeToFile(tg, "readNoData.ser");
        Serializer.deserializeFromFile(tg, "readNoData.ser");
        Assert.assertTrue(Controller.isChainSuccess);

    }






}
