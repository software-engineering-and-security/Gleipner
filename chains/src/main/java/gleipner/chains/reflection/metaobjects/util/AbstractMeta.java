package gleipner.chains.reflection.metaobjects.util;

public abstract class AbstractMeta implements IMeta{

    public AbstractMeta() {}

    public abstract void doMethod(String arg);
    public void publicMethod(String arg) {
    }
}
