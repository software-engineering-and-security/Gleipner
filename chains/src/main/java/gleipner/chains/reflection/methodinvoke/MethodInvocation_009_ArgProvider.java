package gleipner.chains.reflection.methodinvoke;

import java.io.Serializable;

public class MethodInvocation_009_ArgProvider implements Serializable {

    public boolean doInvoke;
    public String taint;

    public MethodInvocation_009_ArgProvider(boolean doInvoke, String taint) {
        this.doInvoke = doInvoke;
        this.taint = taint;
    }

    public boolean isDoInvoke() {
        return doInvoke;
    }

    public String getTaint() {
        return taint;
    }
}
