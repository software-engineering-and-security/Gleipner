package gleipner.chains.ysoserial.hibernate1;

public class Hibernate_AbstractType implements Hibernate_Type{

    public int getHashCode(Object x) {
        return x.hashCode();
    }

}
