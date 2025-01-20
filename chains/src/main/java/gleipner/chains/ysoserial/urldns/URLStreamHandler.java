package gleipner.chains.ysoserial.urldns;

public class URLStreamHandler {

    protected int hashCode(URL u) {
        int h = 0;

        String addr = getHostAdress(u);
        // rest does not matter

        return h;
    }

    protected String getHostAdress(URL u) {
        return u.getHostAddress();
    }



}
