package gleipner;

import gleipner.chains.runtime_exceptions.*;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ExceptionTests {

    TriggerGadget triggerGadget;

    @Before
    public void before() {
        triggerGadget = new TriggerGadget();
        Controller.isChainSuccess = false;
    }

    @Test
    public void arithmeticException001() {
        ArithmeticException_001_Trampoline t = new ArithmeticException_001_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, 0);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void arithmeticException002_FP() {
        ArithmeticException_002_FP_Trampoline t = new ArithmeticException_002_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void arrayStoreException001() {
        ArrayStoreException_001_Trampoline t = new ArrayStoreException_001_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, new Integer(1));
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void arrayStoreException002_FP() {
        ArrayStoreException_002_FP_Trampoline t = new ArrayStoreException_002_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, "foo");
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void emptyStackException001() {
        EmptyStackException_001_Trampoline t = new EmptyStackException_001_Trampoline(10, new SinkGadget(), Controller.TAINT_MARKER);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void emptyStackException002_FP() {
        EmptyStackException_002_FP_Trampoline t = new EmptyStackException_002_FP_Trampoline(10, new SinkGadget(), Controller.TAINT_MARKER);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void indexOutOfBoundsException001() {
        IndexOutOfBoundsException_001_Trampoline t = new IndexOutOfBoundsException_001_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, 20);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void indexOutOfBoundsException002_FP() {
        IndexOutOfBoundsException_002_FP_Trampoline t = new IndexOutOfBoundsException_002_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void negativeArraySizeException001() {
        NegativeArraySizeException_001_Trampoline t = new NegativeArraySizeException_001_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, -1);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void negativeArraySizeException002_FP() {
        NegativeArraySizeException_002_FP_Trampoline t = new NegativeArraySizeException_002_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, -1);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void noSuchElementException001() {
        NoSuchElementException_001_Trampoline t = new NoSuchElementException_001_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, new ArrayList());
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void noSuchElementException002_FP() {
        NoSuchElementException_002_FP_Trampoline t = new NoSuchElementException_002_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, new ArrayList());
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void nullPointerException001() {
        NullPointerException_001_Trampoline t = new NullPointerException_001_Trampoline(new SinkGadget(), Controller.TAINT_MARKER, null);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void nullPointerException002_FP() {
        NullPointerException_002_FP_Trampoline t = new NullPointerException_002_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);
        triggerGadget.o = t;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }
}
