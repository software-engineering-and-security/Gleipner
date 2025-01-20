package gleipner.chains.ysoserial.spring;

import gleipner.core.SinkGadget;

public class SinkInterfaceImpl implements SinkInterface{

    public SinkGadget sinkGadget;
    public String taint;

    public SinkInterfaceImpl() {
    }

    public SinkInterfaceImpl(SinkGadget sinkGadget, String taint) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
    }

    @Override
    public void invokeSink() {
        sinkGadget.sinkMethod(taint);
    }
}
