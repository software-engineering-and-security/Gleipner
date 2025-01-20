package gleipner;

import gleipner.chains.basic.*;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class BasicTests {

    @Test
    public void basic_gadget_chain() throws IOException, ClassNotFoundException {
        // Setup gadget
        BasicSinkGadget sinkGadget = new BasicSinkGadget();
        sinkGadget.sinkGadget = new SinkGadget();
        sinkGadget.tainted = Controller.TAINT_MARKER;

        BasicLinkGadget basicLinkGadget = new BasicLinkGadget();
        basicLinkGadget.basicSinkGadget = sinkGadget;


        TriggerGadget triggerGadget = new TriggerGadget();
        triggerGadget.o = basicLinkGadget;

        Serializer.serializeDeserialize(triggerGadget);

    }

     @Test
    public void basic_gadget_chain_2() throws IOException, ClassNotFoundException {
        BasicTriggerGadget btg = new BasicTriggerGadget();
        btg.sinkGadget = new SinkGadget();
        btg.tainted = Controller.TAINT_MARKER;
        Serializer.serializeDeserialize(btg);

         Assert.assertTrue(Controller.isChainSuccess);
    }

}