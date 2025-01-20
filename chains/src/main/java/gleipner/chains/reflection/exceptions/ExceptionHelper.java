package gleipner.chains.reflection.exceptions;

public class ExceptionHelper extends ParentExceptionHelper{

    public ExceptionHelper() {}


    private int privateInt;
    public int publicInt;

    public void doMethod(int param) {
        // throws arithmetic exception for i = 0
        this.privateInt = 1 / param;
    }






}
