package gleipner;

import gleipner.chains.ysoserial.aspectjweaver.TiedMapEntry;
import gleipner.chains.ysoserial.c3p0.PoolBackedDataSourceBase;
import gleipner.chains.ysoserial.cc1.*;
import gleipner.chains.ysoserial.cc1.ConstantTransformer;
import gleipner.chains.ysoserial.cc1.LazyMap;
import gleipner.chains.ysoserial.cc1.Transformer;
import gleipner.chains.ysoserial.clojure.*;
import gleipner.chains.ysoserial.groovy.ConvertedClosure;
import gleipner.chains.ysoserial.groovy.MethodClosure;
import gleipner.chains.ysoserial.hibernate1.*;
import gleipner.chains.ysoserial.jrmplistener.UnicastRemoteObject;
import gleipner.chains.ysoserial.myfaces1.MyFaces_ValueExpressionImpl;
import gleipner.chains.ysoserial.myfaces1.MyFaces_ValueExpressionMethodExpression;
import gleipner.chains.ysoserial.rome.Rome_GetterSink;
import gleipner.chains.ysoserial.rome.Rome_ObjectBean;
import gleipner.chains.ysoserial.spring.ObjectFactory;
import gleipner.chains.ysoserial.spring.SerializableTypeWrapper;
import gleipner.chains.ysoserial.spring.SinkInterface;
import gleipner.chains.ysoserial.spring.SinkInterfaceImpl;
import gleipner.chains.ysoserial.urldns.URL;
import gleipner.chains.ysoserial.vaadin1.Vaadin_Getter_SinkGadget;
import gleipner.chains.ysoserial.vaadin1.Vaadin_NestedMethodProperty;
import gleipner.chains.ysoserial.vaadin1.Vaadin_PropertySetItem;
import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.TriggerGadget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.*;
import java.util.logging.Logger;

public class YsoserialTests {

    public TriggerGadget triggerGadget;

    @Before
    public void before() {
        Controller.isChainSuccess = false;
        triggerGadget = new TriggerGadget();
    }

    @Test
    public void commonsCollections1() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, NoSuchMethodException {

        ChainedTransformer transformerChain = new ChainedTransformer(new Transformer[] {
                new ConstantTransformer(new SinkGadget()),
                new InvokerTransformer("sinkMethod", new Class[] {
                        String.class}, new String[] {Controller.TAINT_MARKER} ),
                new ConstantTransformer(1)
        });

        Map lazyMap = LazyMap.decorate(new HashMap(), transformerChain);

        Class<?> invocationHandlerClass = Class.forName("gleipner.chains.ysoserial.cc1.AnnotationInvocationHandler");
        Constructor<?> ctor = invocationHandlerClass.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        AnnotationInvocationHandler invocationHandler = (AnnotationInvocationHandler) ctor.newInstance(Override.class, lazyMap);

        IntermediaryMap mapProxy = (IntermediaryMap) Proxy.newProxyInstance(YsoserialTests.class.getClassLoader(), new Class[] {IntermediaryMap.class}, invocationHandler);
        AnnotationInvocationHandler invocationHandler2 = (AnnotationInvocationHandler) ctor.newInstance(Override.class, mapProxy);
        try {
            Serializer.serializeDeserialize(invocationHandler2);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(Controller.isChainSuccess);

    }
    @Test
    public void hibernate() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Hibernate_AbstractComponentTuplizer tuplizer = new Hibernate_AbstractComponentTuplizer(1);
        tuplizer.getters = new Hibernate_GetterMethodImpl[] {new Hibernate_GetterMethodImpl(new SinkGadget())};

        /*
        Field getterField = Hibernate_AbstractComponentTuplizer.class.getField("getters");
        getterField.setAccessible(true);
        getterField.set(tuplizer, new Hibernate_GetterMethodImpl[] {new Hibernate_GetterMethodImpl()});

         */
        Hibernate_Type[] types = new Hibernate_Type[1];
        types[0] = null;
        Hibernate_ComponentType componentType = new Hibernate_ComponentType(1, types, tuplizer);
        Hibernate_TypedValue typedValue = new Hibernate_TypedValue(componentType, Controller.TAINT_MARKER);

        TriggerGadget triggerGadget = new TriggerGadget();
        triggerGadget.o = typedValue;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void aspectJWeaver() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {

        Constructor ctor = Class.forName("gleipner.chains.ysoserial.aspectjweaver.SimpleCache$StoreableCachingMap").getDeclaredConstructors()[0];
        ctor.setAccessible(true);

        Object simpleCache = ctor.newInstance(".", 12, new SinkGadget());
        gleipner.chains.ysoserial.aspectjweaver.Transformer ct = new gleipner.chains.ysoserial.aspectjweaver.ConstantTransformer(Controller.TAINT_MARKER);
        Map lazyMap = gleipner.chains.ysoserial.aspectjweaver.LazyMap.decorate((Map) simpleCache, ct);
        TiedMapEntry entry = new TiedMapEntry(lazyMap, new SinkGadget());

        triggerGadget.o = entry;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void clojure() {

        core$comp$fn__4727 compositeFunction = new core$comp$fn__4727(
                new main$eval_opt(new SinkGadget()),
                new core$constantly$fn__4614(Controller.TAINT_MARKER));

        HashMap<String, IFn> clojureMap = new HashMap<>();
        clojureMap.put("hashCode", compositeFunction);

        AbstractTableModel$ff19274a tableModel = new AbstractTableModel$ff19274a();
        tableModel.__initClojureFnMappings(clojureMap);

        triggerGadget.o = tableModel;
        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void vaadin1() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Vaadin_Getter_SinkGadget getterSinkGadget = new Vaadin_Getter_SinkGadget(Controller.TAINT_MARKER);
        getterSinkGadget.sinkGadget = new SinkGadget();

        Vaadin_PropertySetItem pItem = new Vaadin_PropertySetItem();
        Vaadin_NestedMethodProperty<Object> prop = new Vaadin_NestedMethodProperty<Object>(getterSinkGadget, "outputProperties");
        pItem.addItemProperty("outputProperties", prop);
        triggerGadget.o = pItem;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void myfaces1() throws IOException, ClassNotFoundException {
        MyFaces_ValueExpressionImpl impl = new MyFaces_ValueExpressionImpl();
        impl.sinkGadget = new SinkGadget();
        impl.expr = Controller.TAINT_MARKER;

        MyFaces_ValueExpressionMethodExpression expr = new MyFaces_ValueExpressionMethodExpression(impl);
        HashMap<MyFaces_ValueExpressionMethodExpression,MyFaces_ValueExpressionMethodExpression> map = new HashMap<>();
        map.put(expr, expr);

        Serializer.serializeDeserialize(map);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void rome() throws IOException, ClassNotFoundException {
        Rome_ObjectBean bean = new Rome_ObjectBean(Rome_GetterSink.class, new Rome_GetterSink(Controller.TAINT_MARKER, new SinkGadget()));
        Rome_ObjectBean root = new Rome_ObjectBean(Rome_ObjectBean.class, bean);
        TriggerGadget triggerGadget = new TriggerGadget();
        triggerGadget.o = root;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void groovy1() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

        ConvertedClosure closure = new ConvertedClosure(new MethodClosure(Controller.TAINT_MARKER , "sinkMethod"), "entrySet", new SinkGadget());
        Map map = (Map) Proxy.newProxyInstance(HashMap.class.getClassLoader(), new Class<?>[]{Map.class}, closure);

        Constructor<?> ctor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) ctor.newInstance(Override.class, map);

        try {
            Serializer.serializeDeserialize(handler);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void urldns() {

        URL url = new URL("http", Controller.TAINT_MARKER, "file", null, new SinkGadget());
        triggerGadget.o = url;

        Serializer.serializeDeserialize(triggerGadget);
        Assert.assertTrue(Controller.isChainSuccess);

    }

    @Test
    public void jrmpListener() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor ctor = UnicastRemoteObject.class.getDeclaredConstructor(String.class, SinkGadget.class);
        ctor.setAccessible(true);

        UnicastRemoteObject obj = (UnicastRemoteObject) ctor.newInstance(Controller.TAINT_MARKER, new SinkGadget());
        Serializer.serializeDeserialize(obj);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void spring() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        // observe the entrySet set by provider and HashMap in typeMap ... build this interface connection
        // to the 3times nested Proxies

        HashMap<String, Object> typeMap = new HashMap<>();
        typeMap.put("getObject", new SinkInterfaceImpl(new SinkGadget(), Controller.TAINT_MARKER));

        ObjectFactory factoryProxy = (ObjectFactory) Proxy.newProxyInstance(ObjectFactory.class.getClassLoader(), new Class[] {ObjectFactory.class},
                new gleipner.chains.ysoserial.spring.AnnotationInvocationHandler(Override.class, typeMap));

        Class<?> factoryInvocationHandlerClass = Class.forName("gleipner.chains.ysoserial.spring.AutowireUtils$ObjectFactoryDelegatingInvocationHandler");
        Constructor<?> factoryCtor = factoryInvocationHandlerClass.getDeclaredConstructors()[0];
        factoryCtor.setAccessible(true);

        Method proxyMethod = Proxy.class.getDeclaredMethod("newProxyInstance", ClassLoader.class, Class[].class, InvocationHandler.class);
        Type typeProxy = (Type) proxyMethod.invoke(null, factoryInvocationHandlerClass.getClassLoader(), new Class[] {Type.class, SinkInterface.class}, factoryCtor.newInstance(factoryProxy));

        HashMap<String, Object> invocationHandlerMap = new HashMap<>();
        Class typeProviderClass = Class.forName("gleipner.chains.ysoserial.spring.SerializableTypeWrapper$TypeProvider");
        invocationHandlerMap.put("getType", typeProxy);

        Object t =  Proxy.newProxyInstance(SerializableTypeWrapper.class.getClassLoader(),
                new Class[] {typeProviderClass},
                new gleipner.chains.ysoserial.spring.AnnotationInvocationHandler(Override.class, invocationHandlerMap));

        Method getClassMethod = Object.class.getMethod("getClass");
        Constructor methodInvokeProviderCtor = SerializableTypeWrapper.MethodInvokeTypeProvider.class.getDeclaredConstructors()[0];
        SerializableTypeWrapper.MethodInvokeTypeProvider provider = (SerializableTypeWrapper.MethodInvokeTypeProvider) methodInvokeProviderCtor.newInstance(typeProviderClass.cast(t), getClassMethod);

        Field f = SerializableTypeWrapper.MethodInvokeTypeProvider.class.getDeclaredField("methodName");
        f.setAccessible(true);
        f.set(provider, "invokeSink");

        Serializer.serializeDeserialize(provider);
        Assert.assertTrue(Controller.isChainSuccess);
    }

    @Test
    public void c3p0() throws NoSuchFieldException, IllegalAccessException {
        PoolBackedDataSourceBase base = new PoolBackedDataSourceBase();

        Field dataSourceField = PoolBackedDataSourceBase.class.getDeclaredField("connectionPoolDataSource");
        dataSourceField.setAccessible(true);
        dataSourceField.set(base, new PoolSource( Controller.TAINT_MARKER, Controller.TAINT_MARKER));

        Field sinkField = PoolBackedDataSourceBase.class.getDeclaredField("sinkGadget");
        sinkField.setAccessible(true);
        sinkField.set(base, new SinkGadget());

        Serializer.serializeDeserialize(base);
        Assert.assertTrue(Controller.isChainSuccess);


    }

    // from ysoserial ...
    // https://github.com/frohoff/ysoserial/blob/master/src/main/java/ysoserial/payloads/C3P0.java
    private static final class PoolSource implements ConnectionPoolDataSource, Referenceable {

        private String className;
        private String url;

        public PoolSource ( String className, String url ) {
            this.className = className;
            this.url = url;
        }

        @Override
        public PooledConnection getPooledConnection() throws SQLException {
            return null;
        }

        @Override
        public PooledConnection getPooledConnection(String user, String password) throws SQLException {
            return null;
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {

        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {

        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }

        @Override
        public Reference getReference() throws NamingException {
            return new Reference("", this.className, this.url);
        }
    }

}
