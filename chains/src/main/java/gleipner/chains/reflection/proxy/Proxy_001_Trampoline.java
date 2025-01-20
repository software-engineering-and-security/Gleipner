package gleipner.chains.reflection.proxy;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.InterProcedural;

public class Proxy_001_Trampoline extends GleipnerObject {

    public ProxyInterface proxy;
    public String taint;

    public Proxy_001_Trampoline() {
    }

    public Proxy_001_Trampoline(ProxyInterface proxy, String taint) {
        this.proxy = proxy;
        this.taint = taint;
    }

    @Override
    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    public int hashCode() {
        proxy.invokeSink(taint);
        return super.hashCode();
    }

}
