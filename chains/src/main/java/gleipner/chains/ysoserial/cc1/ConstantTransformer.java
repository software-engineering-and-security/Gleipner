package gleipner.chains.ysoserial.cc1;

import java.io.Serializable;

public class ConstantTransformer implements Transformer, Serializable {

    private final Object iConstant;

    public ConstantTransformer(Object constantToReturn) {
        super();
        iConstant = constantToReturn;
    }

    @Override
    public Object transform(Object input) {
        return iConstant;
    }
}
