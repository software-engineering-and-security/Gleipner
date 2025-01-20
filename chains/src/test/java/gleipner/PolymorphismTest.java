package gleipner;
import gleipner.chains.polymorphism.Polymorphism_001_LinkGadget;
import gleipner.chains.polymorphism.Polymorphism_001_SinkGadget;
import gleipner.chains.polymorphism.Polymorphism_001_TrampolineGadget;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PolymorphismTest {

    TriggerGadget triggerGadget;
    @Before
    public void before() {
        triggerGadget = new TriggerGadget();
        Controller.isChainSuccess = false;
    }

    @Test
    public void polymorphismTest() throws IOException, ClassNotFoundException {
        Polymorphism_001_SinkGadget sinkGadget = new Polymorphism_001_SinkGadget();
        sinkGadget.sinkGadget = new SinkGadget();
        sinkGadget.tainted = Controller.TAINT_MARKER;

        Polymorphism_001_LinkGadget linkGadget = new Polymorphism_001_LinkGadget();
        linkGadget.sinkGadget = sinkGadget;

        Polymorphism_001_TrampolineGadget tg = new Polymorphism_001_TrampolineGadget();
        tg.linkGadget = linkGadget;

        triggerGadget.o = tg;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }


}
