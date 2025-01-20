package gleipner.chains.ysoserial.myfaces1;

import gleipner.core.Controller;
import gleipner.core.SinkGadget;
import gleipner.core.annotations.ChainLength;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.SinkAccessPaths;
import gleipner.core.annotations.Ysoserial;

import java.io.Serializable;

public final class MyFaces_ValueExpressionImpl extends MyFaces_ValueExpression implements Serializable {

    public Class expectedType;
    public String expr;
    public SinkGadget sinkGadget;

    public MyFaces_ValueExpressionImpl() {
    }

    public MyFaces_ValueExpressionImpl(SinkGadget sinkGadget, String expr) {
        this.sinkGadget = sinkGadget;
        this.expr = expr;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @SinkAccessPaths(1)
    @ChainLength(6)
    @Ysoserial
    public Object getValue(MyFaces_ELContext context)  {
        sinkGadget.sinkMethod(expr);
        return null;
    }

}
