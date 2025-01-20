package gleipner.chains.reflection.methodinvoke;

import java.io.Serializable;

public class MethodInvocation_010_FP_ArgProvider implements Serializable {

    public static final boolean doInvoke = false;
    public String taint;

    public MethodInvocation_010_FP_ArgProvider(boolean doInvoke, String taint) {
        this.taint = taint;
    }

    public boolean isDoInvoke() {
        return MethodInvocation_010_FP_ArgProvider.doInvoke;
    }

    public String getTaint() {
        return taint;
    }
}
