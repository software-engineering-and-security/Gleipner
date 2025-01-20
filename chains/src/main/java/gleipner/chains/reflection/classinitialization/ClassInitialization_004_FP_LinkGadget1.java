package gleipner.chains.reflection.classinitialization;

public class ClassInitialization_004_FP_LinkGadget1 {

    public static boolean invoke = true;

    static {
        try {
            ClassInitialization_004_FP_LinkGadget1.invoke = false;
            Class.forName("gleipner.chains.reflection.classinitialization.ClassInitialization_004_FP_LinkGadget2");
        } catch (ClassNotFoundException ignored) {

        }
    }
}
