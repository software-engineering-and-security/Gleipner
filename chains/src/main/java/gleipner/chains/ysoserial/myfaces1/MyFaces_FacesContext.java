package gleipner.chains.ysoserial.myfaces1;

public class MyFaces_FacesContext {

    MyFaces_ELContext mockElContext;
    static MyFaces_FacesContext instance;
    private MyFaces_FacesContext() {}

    public static MyFaces_FacesContext getCurrentInstance() {
        if (instance == null) {
            instance = new MyFaces_FacesContext();
            instance.mockElContext = new MyFaces_ELContext();
        }
        return instance;
    }

    public MyFaces_ELContext getELContext() {
        return mockElContext;
    }



}
