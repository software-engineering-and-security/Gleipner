package gleipner.chains.ysoserial.myfaces1;

import gleipner.core.GleipnerObject;
import gleipner.core.annotations.InterProcedural;
import gleipner.core.annotations.IntraProcedural;

import java.io.Serializable;

public class MyFaces_ValueExpressionMethodExpression extends GleipnerObject implements Serializable {

    private static final long serialVersionUID = -2847633717581167765L;
    private MyFaces_ValueExpression valueExpression;

    public MyFaces_ValueExpressionMethodExpression(MyFaces_ValueExpression valueExpression)
    {
        this.valueExpression = valueExpression;
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @Override
    public int hashCode() {
        MyFaces_MethodExpression me = getMethodExpression();
        if (me != null)
        {
            return me.hashCode();
        }
        return valueExpression.hashCode();
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    private MyFaces_MethodExpression getMethodExpression()
    {
        return getMethodExpression(MyFaces_FacesContext.getCurrentInstance().getELContext());
    }

    @InterProcedural(InterProcedural.VIRTUAL_INVOKE)
    @IntraProcedural(IntraProcedural.REFLECTION)
    private MyFaces_MethodExpression getMethodExpression(MyFaces_ELContext context)
    {
        Object meOrVe = valueExpression.getValue(context);
        if (meOrVe instanceof MyFaces_MethodExpression)
        {
            return (MyFaces_MethodExpression) meOrVe;
        }
        else if (meOrVe instanceof MyFaces_ValueExpression)
        {
            while (meOrVe != null && meOrVe instanceof MyFaces_ValueExpression)
            {
                meOrVe = ((MyFaces_ValueExpression)meOrVe).getValue(context);
            }
            return (MyFaces_MethodExpression) meOrVe;
        }
        else
        {
            return null;
        }
    }

}
