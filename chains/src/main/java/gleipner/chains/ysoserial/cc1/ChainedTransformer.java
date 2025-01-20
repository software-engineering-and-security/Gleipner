package gleipner.chains.ysoserial.cc1;

import java.io.Serializable;

public class ChainedTransformer implements Transformer, Serializable {

    private final Transformer[] iTransformers;


    @Override
    public Object transform(Object object) {
        for (int i = 0; i < iTransformers.length; i++) {
            object = iTransformers[i].transform(object);
        }
        return object;
    }

    public ChainedTransformer(Transformer[] transformers) {
        super();
        iTransformers = transformers;
    }
}
