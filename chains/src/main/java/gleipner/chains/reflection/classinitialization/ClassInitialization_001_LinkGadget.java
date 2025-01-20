package gleipner.chains.reflection.classinitialization;

import gleipner.core.annotations.InterProcedural;

public class ClassInitialization_001_LinkGadget {


    static {
        ClassInitialization_001_Singleton.getInstance().invokeSinkGadget();
    }
}
