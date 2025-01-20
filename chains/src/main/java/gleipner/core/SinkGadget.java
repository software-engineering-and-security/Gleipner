package gleipner.core;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SinkGadget implements Serializable {

    public SinkGadget() {
    }

    public void sinkMethod(String taint) {
        // we do this to have the compiler compile, even though the invokeSinkMethod is known to throw an Exception
        Controller.invokeSink(taint);
    }

}
