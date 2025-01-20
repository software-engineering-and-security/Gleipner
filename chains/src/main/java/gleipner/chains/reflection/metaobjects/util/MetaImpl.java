package gleipner.chains.reflection.metaobjects.util;

@MetaAnnotation(1)
@MetaAnnotation2(10)
public class MetaImpl extends AbstractMeta{

    public MetaImpl(int i) {
    }

    private int privateField;

    private MetaImpl(int i, int j) {
    }

    @Override
    public void doMethod(String arg) {
    }


    private String privateMethod(String arg) {
        return arg;
    }

    @MetaAnnotation(2)
    @MetaAnnotation2(10)
    public class InnerMeta1 {
        public int a;
        public int b;
        public int c;
    }

    @MetaAnnotation(3)
    @MetaAnnotation2(11)
    public class InnerMeta2 extends InnerMeta1 {
        public int i;
        public int j;
        public int k;
    }
}
