package gleipner.chains.runtime_exceptions;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class NoSuchElementException_001_Trampoline extends GleipnerObject {

    public SinkGadget sinkGadget;
    public String taint;
    public List l;

    public NoSuchElementException_001_Trampoline(SinkGadget sinkGadget, String taint, List l) {
        this.sinkGadget = sinkGadget;
        this.taint = taint;
        this.l = l;
    }

    @Override
    public int hashCode() {

        Iterator iter = l.iterator();

        try {
            iter.next();
        } catch (NoSuchElementException e) {
            sinkGadget.sinkMethod(taint);
        }

        return super.hashCode();
    }
}
