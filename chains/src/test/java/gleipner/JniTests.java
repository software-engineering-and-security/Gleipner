package gleipner;

import gleipner.chains.jni.Custom001Trampoline;
import gleipner.chains.jni.Thread_001_Trampoline;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Test;

import java.io.IOException;

public class JniTests {

    @Test
    public void JniTest() throws IOException, ClassNotFoundException {
        Thread_001_Trampoline t = new Thread_001_Trampoline();
        t.tainted = "taint";

        TriggerGadget trigger = new TriggerGadget();
        trigger.o = t;

        Serializer.serializeDeserialize(trigger);
    }

    @Test
    public void customJniTest() {
        Custom001Trampoline trampoline = new Custom001Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        TriggerGadget trigger = new TriggerGadget();
        trigger.o = trampoline;

        Serializer.serializeDeserialize(trigger);
    }
}
