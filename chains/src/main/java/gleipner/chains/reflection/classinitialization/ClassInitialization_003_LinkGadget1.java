package gleipner.chains.reflection.classinitialization;

public class ClassInitialization_003_LinkGadget1 {

    static {
        try {
            Class.forName("gleipner.chains.reflection.classinitialization.ClassInitialization_003_LinkGadget2");
        } catch (ClassNotFoundException | ExceptionInInitializerError ignored) {

        }
    }
}
