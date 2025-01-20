package gleipner.chains.jni;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.io.File;

public class Custom001Trampoline extends GleipnerObject {

    static {
        String os =System.getProperty("os.name").toLowerCase();
        if(os.contains("win")){
            try {
                System.load(Custom001Trampoline.class.getResource("/custom_win.dll").getPath());
            } catch (Exception e) {
                System.load(new File("C:\\TEMP\\custom_win.dll").getPath());
            }
        } else if(os.contains("osx")) {
            System.err.println("not implemented for osx");
        } else if(os.contains("nix") || os.contains("nux")) {
            try {
                System.load(Custom001Trampoline.class.getResource("/custom.so").getPath());
            } catch (Exception e) {
                System.load(new File("/tmp/custom.so").getPath());
            }
        }
    }

    public SinkGadget sinkGadget;
    public String taint;

    public Custom001Trampoline() {
    }

    public Custom001Trampoline(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public int hashCode() {
        native0();
        return super.hashCode();
    }

    private native void native0();

    public void target() {
        sinkGadget.sinkMethod(taint);
    }
}
