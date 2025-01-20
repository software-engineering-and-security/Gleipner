package gleipner.chains.reflection.proxy;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.FalsePositive;

public class Proxy_005_FP_Trampoline extends GleipnerObject {

    public Proxy_005_FP_Interface1 iface;
    public String taint;

    public Proxy_005_FP_Trampoline(Proxy_005_FP_Interface1 iface, String taint) {
        this.iface = iface;
        this.taint = taint;
    }

    @Override
    @FalsePositive
    public int hashCode() {
        this.iface.doMethod(this.taint);

        return super.hashCode();
    }
}
