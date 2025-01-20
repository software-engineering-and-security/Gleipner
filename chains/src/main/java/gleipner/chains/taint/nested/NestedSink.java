package gleipner.chains.taint.nested;

import gleipner.core.SinkGadget;

public class NestedSink extends Nested{

    private SinkGadget sinkGadget;

    public NestedSink(SinkGadget s, int size) {
        super(size);
        this.sinkGadget = s;
    }

    public NestedSink(SinkGadget s) {
        super(0);
        this.sinkGadget = s;
    }

    @Override
    public String invoke(String taint) {
        if (taint != null) {
            sinkGadget.sinkMethod(taint);
        }
        return null;
    }
}
