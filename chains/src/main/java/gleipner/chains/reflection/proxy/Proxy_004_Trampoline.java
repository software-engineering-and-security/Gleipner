package gleipner.chains.reflection.proxy;

import gleipner.core.GleipnerObject;

public class Proxy_004_Trampoline extends GleipnerObject {

    public Proxy_004_Interface1 iface;
    public String taint;

    public Proxy_004_Trampoline(Proxy_004_Interface1 iface, String taint) {
        this.iface = iface;
        this.taint = taint;
    }

    @Override
    public int hashCode() {
        this.iface.doMethod(this.taint);

        return super.hashCode();
    }
}
