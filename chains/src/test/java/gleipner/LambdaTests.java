package gleipner;

import gleipner.chains.lambda.Lambda_001_Trampoline;
import gleipner.chains.lambda.Lambda_002_FP_Trampoline;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;


public class LambdaTests {

    TriggerGadget triggerGadget;

    @Before
    public void before() {
        triggerGadget = new TriggerGadget();
        Controller.isChainSuccess = false;
    }

    @Test
    public void lambda001() {

        Lambda_001_Trampoline trampoline = new Lambda_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void lambda002() {

        Lambda_002_FP_Trampoline trampoline = new Lambda_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);

    }

}
