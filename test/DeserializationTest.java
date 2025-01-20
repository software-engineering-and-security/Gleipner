import gleipner.chains.jni.Custom001Trampoline;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeserializationTest {

    public static void main(String args[]) {

        if (args[0].equals("serialize")) {
            Custom001Trampoline trampoline = new Custom001Trampoline();
            trampoline.sinkGadget = new SinkGadget();
            trampoline.taint = Controller.TAINT_MARKER;

            TriggerGadget trigger = new TriggerGadget();
            trigger.o = trampoline;
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(args[1]));
                oos.writeObject(trigger);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]));
                ois.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}