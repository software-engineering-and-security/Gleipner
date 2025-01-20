package gleipner.chains.ysoserial.aspectjweaver;

import java.io.Serializable;

public class ConstantTransformer implements Transformer, Serializable {

    public static final Transformer NULL_INSTANCE = new ConstantTransformer(null);
    private final Object iConstant;

    public static Transformer getInstance(Object constantToReturn) {
        if (constantToReturn == null) {
            return NULL_INSTANCE;
        }
        return new ConstantTransformer(constantToReturn);
    }
    public ConstantTransformer(Object constantToReturn) {
        super();
        iConstant = constantToReturn;
    }
    @Override
    public Object transform(Object input) {
        return iConstant;
    }
}
