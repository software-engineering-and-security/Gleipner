package gleipner;

import gleipner.chains.specialinvoke.SpecialInvoke_001_Trampoline;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class InvokeSpecialTests {

    @Test
    public void invokeSpecial001() throws IOException, ClassNotFoundException {
        // Setup gadget
        TriggerGadget triggerGadget = new TriggerGadget();
        triggerGadget.o = new SpecialInvoke_001_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

}
