package gleipner;

import gleipner.chains.taint.*;
import gleipner.chains.taint.nested.Nested;
import gleipner.chains.taint.nested.NestedSink;
import gleipner.chains.taint.nested.NestedString;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PayloadTests {

    TriggerGadget triggerGadget;
    @Before
    public void before() {
        triggerGadget = new TriggerGadget();
        Controller.isChainSuccess = false;
    }


    @Test
    public void nested001() {
        NestedSink sink = new NestedSink(new SinkGadget());

        Nested n1 = new Nested(sink);
        Nested n2 = new Nested(n1);
        Nested n3 = new Nested(n2);

        Taint_001_Trampoline tg = new Taint_001_Trampoline(n3, Controller.TAINT_MARKER);

        triggerGadget.o = tg;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void nested003() {
        NestedSink sink = new NestedSink(new SinkGadget());
        NestedString cmp = new NestedString("foo");

        Nested leaf = new Nested(sink, cmp);
        Nested root = new Nested(leaf, cmp);

        Taint_003_Trampoline tg = new Taint_003_Trampoline(root, Controller.TAINT_MARKER);

        triggerGadget.o = tg;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void nested004() {
        NestedSink sink = new NestedSink(new SinkGadget());
        NestedString cmd = new NestedString(Controller.TAINT_MARKER);
        Nested dummy = new Nested(0);

        Nested substructure_child = new Nested(3);
        substructure_child.set(2, dummy);

        Nested substructure_root = new Nested(2);
        substructure_root.set(1, substructure_child);

        Nested root1 = new Nested(2);
        root1.set(1, cmd);
        root1.set(0, substructure_root);

        Nested root2 = new Nested(2);
        root2.set(1, sink);
        root2.set(0, substructure_root);

        Taint_004_Trampoline tg = new Taint_004_Trampoline(root1, root2);
        triggerGadget.o = tg;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void nested006() {
        Nested root = new Nested(3);
        NestedSink sink = new NestedSink(new SinkGadget(), 1);
        root.set(0, sink);
        root.set(1, sink);
        root.set(2, new NestedString(Controller.TAINT_MARKER));
        Nested dummy0 = new Nested(1);
        sink.set(0, dummy0);
        Nested dummy1 = new Nested(1);
        dummy0.set(0, dummy1);
        Nested dummy2 = new Nested(1);
        dummy1.set(0, dummy2);

        Taint_006_Trampoline tg = new Taint_006_Trampoline();
        tg.nested = root;
        triggerGadget.o = tg;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void nested008() {
        Nested root = new Nested(3);
        NestedSink sink = new NestedSink(new SinkGadget(), 1);
        root.set(0, sink);
        root.set(1, sink);
        root.set(2, new NestedString(Controller.TAINT_MARKER));
        Nested dummy0 = new Nested(1);
        sink.set(0, dummy0);
        Nested dummy1 = new Nested(1);
        dummy0.set(0, dummy1);
        Nested dummy2 = new Nested(1);
        dummy1.set(0, dummy2);
        Nested dummy3 = new Nested(1);
        dummy2.set(0, dummy3);
        Nested dummy4 = new Nested(1);
        dummy3.set(0, dummy4);
        Taint_008_Trampoline tg = new Taint_008_Trampoline();

        tg.nested = root;
        triggerGadget.o = tg;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void nested010() {
        Nested root = new Nested(3);
        NestedSink sink = new NestedSink(new SinkGadget(), 1);
        root.set(0, sink);
        root.set(1, sink);
        root.set(2, new NestedString(Controller.TAINT_MARKER));
        Nested dummy0 = new Nested(1);
        sink.set(0, dummy0);
        Nested dummy1 = new Nested(1);
        dummy0.set(0, dummy1);
        Nested dummy2 = new Nested(1);
        dummy1.set(0, dummy2);
        Nested dummy3 = new Nested(1);
        dummy2.set(0, dummy3);
        Nested dummy4 = new Nested(1);
        dummy3.set(0, dummy4);
        Nested dummy5 = new Nested(1);
        dummy4.set(0, dummy5);
        Taint_010_Trampoline tg = new Taint_010_Trampoline();
        tg.nested = root;
        triggerGadget.o = tg;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void nested011() {
        Nested sink = new NestedSink(new SinkGadget(), 3);
        Nested taint = new NestedString(Controller.TAINT_MARKER, 3);

        NestedString str = new NestedString("foo");

        sink.set(2, str);
        taint.set(2, str);
        sink.set(0, sink);
        taint.set(0, taint);

        Taint_011_Trampoline trampoline = new Taint_011_Trampoline();
        trampoline.n1 = sink;
        trampoline.n2 = taint;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void nested013() {

        Nested root = new Nested(2);
        Nested prev = root;

        for (int i = 0; i < 10; i++) {
            Nested next = new Nested(2);
            prev.set(0, next);
            prev.set(1, next);
            prev = next;
        }

        prev.set(0, new NestedSink(new SinkGadget()));
        prev.set(1, new NestedString(Controller.TAINT_MARKER));

        Taint_013_Trampoline trampoline = new Taint_013_Trampoline();
        trampoline.nested = root;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void nested015() {

        Nested root = new Nested(3);
        root.set(0, new NestedString("foo"));
        root.set(1, new NestedSink(new SinkGadget()));
        root.set(2, new NestedString(Controller.TAINT_MARKER));

        Taint_015_Trampoline trampoline = new Taint_015_Trampoline();
        trampoline.nested = root;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }
}
