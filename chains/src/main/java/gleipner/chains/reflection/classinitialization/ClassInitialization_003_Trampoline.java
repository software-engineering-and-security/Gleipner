package gleipner.chains.reflection.classinitialization;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

public class ClassInitialization_003_Trampoline extends GleipnerObject {

    public static SinkGadget GlobalSink;
    public static String GlobalTaint;

    public SinkGadget sinkGadget;
    public String taint;

    public ClassInitialization_003_Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {

        GlobalSink = this.sinkGadget;
        GlobalTaint = this.taint;

        try {
            Class.forName("gleipner.chains.reflection.classinitialization.ClassInitialization_003_LinkGadget1");
        } catch (ClassNotFoundException ignored) {

        }

        return super.hashCode();
    }
}
