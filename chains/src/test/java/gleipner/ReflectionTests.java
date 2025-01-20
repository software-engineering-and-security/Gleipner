package gleipner;

import gleipner.chains.reflection.classinitialization.ClassInitialization_002_FP_Trampoline;
import gleipner.chains.reflection.classinitialization.ClassInitialization_003_Trampoline;
import gleipner.chains.reflection.classinitialization.ClassInitialization_004_FP_Trampoline;
import gleipner.chains.reflection.classloading.Classloading_003_Trampoline;
import gleipner.chains.reflection.constructor.*;
import gleipner.chains.reflection.metaobjects.*;
import gleipner.chains.reflection.classinitialization.ClassInitialization_001_Trampoline;
import gleipner.chains.reflection.classloading.Classloading_001_Trampoline;
import gleipner.chains.reflection.classloading.Classloading_002_FP_Trampoline;
import gleipner.chains.reflection.exceptions.*;
import gleipner.chains.reflection.metaobjects.util.MetaImpl;
import gleipner.chains.reflection.methodinvoke.*;
import gleipner.chains.reflection.proxy.*;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Proxy;

public class ReflectionTests {

    TriggerGadget triggerGadget;

    @Before
    public void before() {
        triggerGadget = new TriggerGadget();
        Controller.isChainSuccess = false;
    }

    @Test
    public void proxy001()  {

        Proxy_001_InvocationHandler invocationHandler = new Proxy_001_InvocationHandler();
        invocationHandler.sink = new SinkGadget();

        ProxyInterface proxy = (ProxyInterface) Proxy.newProxyInstance(ProxyInterfaceImpl.class.getClassLoader(),
                new Class<?>[] {ProxyInterface.class},
                invocationHandler);

        Proxy_001_Trampoline proxyTriggerGadget = new Proxy_001_Trampoline();
        proxyTriggerGadget.proxy = proxy;
        proxyTriggerGadget.taint = Controller.TAINT_MARKER;

        triggerGadget.o = proxyTriggerGadget;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void proxy002() {
        Proxy_002_Trampoline trampoline = new Proxy_002_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void proxy003() {
        Proxy_003_FP_Trampoline trampoline = new Proxy_003_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void proxy004() {
        Proxy_004_InvocationHandler invocationHandler = new Proxy_004_InvocationHandler();
        invocationHandler.sinkGadget = new SinkGadget();
        invocationHandler.iface = (Proxy_004_Interface2) Proxy.newProxyInstance(
                Proxy_004_InterfaceImpl.class.getClassLoader(), new Class[] {Proxy_004_Interface2.class}, invocationHandler);

        triggerGadget.o = new Proxy_004_Trampoline(
                (Proxy_004_Interface1) Proxy.newProxyInstance(Proxy_004_InterfaceImpl.class.getClassLoader(), new Class[] {Proxy_004_Interface1.class}, invocationHandler),
                Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void proxy005_FP() {
        Proxy_005_FP_InvocationHandler invocationHandler = new Proxy_005_FP_InvocationHandler();
        invocationHandler.sinkGadget = new SinkGadget();
        invocationHandler.iface = (Proxy_005_FP_Interface2) Proxy.newProxyInstance(
                ReflectionTests.class.getClassLoader(), new Class[] {Proxy_005_FP_Interface2.class}, invocationHandler);

        triggerGadget.o = new Proxy_005_FP_Trampoline(
                (Proxy_005_FP_Interface1) Proxy.newProxyInstance(ReflectionTests.class.getClassLoader(), new Class[] {Proxy_005_FP_Interface1.class}, invocationHandler),
                Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void constructor001() {
        triggerGadget.o = new Constructor_001_Trampoline(
                new Class<?>[] {SinkGadget.class, String.class}, new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void constructor002_FP() {
        triggerGadget.o = new Constructor_002_FP_Trampoline(
                SinkGadget.class, new SinkGadget());

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void constructor003() {
        triggerGadget.o = new Constructor_003_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void constructor004_FP() {
        triggerGadget.o = new Constructor_004_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void constructor005() {
        triggerGadget.o = new Constructor_005_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void constructor006_FP() {
        triggerGadget.o = new Constructor_006_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }


    @Test
    public void classInitialization001() {
        ClassInitialization_001_Trampoline tg = new ClassInitialization_001_Trampoline();
        tg.sinkGadget = new SinkGadget();
        tg.taint = Controller.TAINT_MARKER;
        triggerGadget.o = tg;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void classInitialization002_FP() {
        ClassInitialization_002_FP_Trampoline tg = new ClassInitialization_002_FP_Trampoline();
        tg.sinkGadget = new SinkGadget();
        tg.taint = Controller.TAINT_MARKER;
        triggerGadget.o = tg;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void classInitialization003() {
        triggerGadget.o = new ClassInitialization_003_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void classInitialization004_FP() {
        triggerGadget.o = new ClassInitialization_004_FP_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation001() {

        triggerGadget.o = new MethodInvocation_001_Trampoline(
                new SinkGadget(),
                "sinkMethod",
                new Class[] {String.class},
                Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation002_FP() {

        triggerGadget.o = new MethodInvocation_002_FP_Trampoline(
                new SinkGadget(),
                "sinkMethod",
                new Class[] {String.class},
                Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation003() {
        triggerGadget.o = new MethodInvocation_003_Trampoline(
                new SinkGadget(),
                Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation004_FP() {
        triggerGadget.o = new MethodInvocation_004_FP_Trampoline(
                new SinkGadget(),
                Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation005() {
        triggerGadget.o = new MethodInvocation_005_Trampoline(
                new MethodInvocation_005_LinkGadget(new SinkGadget()),
                Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation006_FP() {
        triggerGadget.o = new MethodInvocation_006_FP_Trampoline(
                new MethodInvocation_006_FP_LinkGadget(new SinkGadget()),
                Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation007() {
        triggerGadget.o = new MethodInvocation_007_Trampoline(
                new MethodInvocation_007_LinkGadget(new SinkGadget(), Controller.TAINT_MARKER));

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation008_FP() {
        triggerGadget.o = new MethodInvocation_008_FP_Trampoline(
                new MethodInvocation_008_FP_LinkGadget(new SinkGadget(), Controller.TAINT_MARKER));

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation009() {
        triggerGadget.o = new MethodInvocation_009_Trampoline(
                new MethodInvocation_009_ArgProvider(true, Controller.TAINT_MARKER),
                "Taint", "DoInvoke", new SinkGadget()
        );

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void methodInvocation010_FP() {
        triggerGadget.o = new MethodInvocation_010_FP_Trampoline(
                new MethodInvocation_010_FP_ArgProvider(true, Controller.TAINT_MARKER),
                "DoInvoke", "Taint", new SinkGadget()
        );

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }


     @Test
    public void classLoading001()  {
        Classloading_001_Trampoline tg = new Classloading_001_Trampoline();
        tg.sinkGadget = new SinkGadget();
        tg.taint = Controller.TAINT_MARKER;

        triggerGadget.o = tg;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void classLoading002_FP()  {
        Classloading_002_FP_Trampoline tg = new Classloading_002_FP_Trampoline();
        tg.sinkGadget = new SinkGadget();
        tg.taint = Controller.TAINT_MARKER;

        triggerGadget.o = tg;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void classLoading003()  {
        triggerGadget.o = new Classloading_003_Trampoline(new SinkGadget(), Controller.TAINT_MARKER);

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void class_not_found_exception001() {
        ClassNotFoundException_001_Trampoline trampoline = new ClassNotFoundException_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void class_not_found_exception002_FP() {
        ClassNotFoundException_002_FP_Trampoline trampoline = new ClassNotFoundException_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void no_such_method_exception001() {
        NoSuchMethodException_001_Trampoline trampoline = new NoSuchMethodException_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void no_such_method_exception002_FP() {
        NoSuchMethodException_002_FP_Trampoline trampoline = new NoSuchMethodException_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void no_such_field_exception001() {
        NoSuchFieldException_001_Trampoline trampoline = new NoSuchFieldException_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void no_such_field_exception002_FP() {
        NoSuchFieldException_002_FP_Trampoline trampoline = new NoSuchFieldException_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void illegal_access_exception001() {
        IllegalAccessException_001_Trampoline trampoline = new IllegalAccessException_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void illegal_access_exception002_FP() {
        IllegalAccessException_002_FP_Trampoline trampoline = new IllegalAccessException_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void instantiation_exception001() {
        InstantiationException_001_Trampoline trampoline = new InstantiationException_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void instantiation_exception002_FP() {
        InstantiationException_002_FP_Trampoline trampoline = new InstantiationException_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void invocation_target_exception001() {
        InvocationTargetException_001_Trampoline trampoline = new InvocationTargetException_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;
        trampoline.param = 0;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void invocation_target_exception002_FP() {
        InvocationTargetException_002_FP_Trampoline trampoline = new InvocationTargetException_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void class_cast_exception001() {
        ClassCastException_001_Trampoline trampoline = new ClassCastException_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void class_cast_exception002_FP() {
        ClassCastException_002_FP_Trampoline trampoline = new ClassCastException_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects001() {
        MetaObjects_001_Trampoline trampoline = new MetaObjects_001_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects002_FP() {
        MetaObjects_002_FP_Trampoline trampoline = new MetaObjects_002_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects003() {
        MetaObjects_003_Trampoline trampoline = new MetaObjects_003_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects004_FP() {
        MetaObjects_004_FP_Trampoline trampoline = new MetaObjects_004_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects005() {
        MetaObjects_005_Trampoline trampoline = new MetaObjects_005_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects006_FP() {
        MetaObjects_006_FP_Trampoline trampoline = new MetaObjects_006_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }
    @Test
    public void metaobjects007() {
        MetaObjects_007_Trampoline trampoline = new MetaObjects_007_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects008_FP() {
        MetaObjects_008_FP_Trampoline trampoline = new MetaObjects_008_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }
    @Test
    public void metaobjects009() {
        MetaObjects_009_Trampoline trampoline = new MetaObjects_009_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects010_FP() {
        MetaObjects_010_FP_Trampoline trampoline = new MetaObjects_010_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects011() {
        MetaObjects_011_Trampoline trampoline = new MetaObjects_011_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects012_FP() {
        MetaObjects_012_FP_Trampoline trampoline = new MetaObjects_012_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects013() {
        MetaObjects_013_Trampoline trampoline = new MetaObjects_013_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects014_FP() {
        MetaObjects_014_FP_Trampoline trampoline = new MetaObjects_014_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects015() {
        MetaObjects_015_Trampoline trampoline = new MetaObjects_015_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects016_FP() {
        MetaObjects_016_FP_Trampoline trampoline = new MetaObjects_016_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects017() {
        MetaObjects_017_Trampoline trampoline = new MetaObjects_017_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;
        trampoline.iMeta = new MetaImpl(1);

        triggerGadget.o = trampoline;


        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void metaobjects018_FP() {
        MetaObjects_018_FP_Trampoline trampoline = new MetaObjects_018_FP_Trampoline();
        trampoline.sinkGadget = new SinkGadget();
        trampoline.taint = Controller.TAINT_MARKER;
        trampoline.iMeta = new MetaImpl(1);

        triggerGadget.o = trampoline;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }

    @Test
    public void arrays001() {
        MetaObjects_019_Trampoline tg = new MetaObjects_019_Trampoline();
        tg.objects = new Serializable[] {
                "", "", new SinkGadget(), Controller.TAINT_MARKER
        };


        triggerGadget.o = tg;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void arrays002() {
        MetaObjects_021_Trampoline tg = new MetaObjects_021_Trampoline();
        tg.objects = new Serializable[] {
                "sinkMethod", new SinkGadget(), Controller.TAINT_MARKER
        };

        triggerGadget.o = tg;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void arrays003() {
        MetaObjects_020_FP_Trampoline tg = new MetaObjects_020_FP_Trampoline();
        tg.objects = new Serializable[] {
                new SinkGadget(), Controller.TAINT_MARKER
        };

        triggerGadget.o = tg;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertFalse(Controller.isChainSuccess);
    }


}
