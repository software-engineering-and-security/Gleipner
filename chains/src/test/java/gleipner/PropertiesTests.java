package gleipner;

import gleipner.chains.keywords.*;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class PropertiesTests {

    TriggerGadget triggerGadget;

    @Before
    public void before() {
        triggerGadget = new TriggerGadget();
        Controller.isChainSuccess = false;
    }

    @Test
    public void transient_001() {
        triggerGadget.o = new Transient_001_TrampolineMethod(Controller.TAINT_MARKER, new SinkGadget());

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void transient_002_fp() {
        triggerGadget.o = new Transient_002_FP_TrampolineMethod(Controller.TAINT_MARKER, new SinkGadget());
        try {
            Serializer.serializeDeserialize(triggerGadget);
        } catch (NullPointerException e) {}
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void serializable_001() {
        triggerGadget.o = new Serializable_001_TrampolineMethod( new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void serializable_002_fp() {
        HashMap<Serializable_002_FP_TrampolineMethod, String> map = new HashMap<>();

        map.put(new Serializable_002_FP_TrampolineMethod(new SinkGadget(), Controller.TAINT_MARKER), "");
        // map.put calls hashCode, so reset to check deserialization
        Controller.isChainSuccess = false;

        Serializer.serializeDeserialize(map);
        Assert.assertFalse(Controller.isChainSuccess);
    }


}
