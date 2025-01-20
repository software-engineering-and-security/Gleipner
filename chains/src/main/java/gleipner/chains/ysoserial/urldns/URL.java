package gleipner.chains.ysoserial.urldns;

import gleipner.core.GleipnerObject;
import gleipner.core.SinkGadget;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.net.MalformedURLException;
import java.util.Hashtable;

public class URL extends GleipnerObject {

    static final String BUILTIN_HANDLERS_PREFIX = "sun.net.www.protocol";

    private int hashCode = -1;
    private String file;
    private String host;
    private String protocol;
    private SinkGadget sinkGadget;


    transient URLStreamHandler handler;
    private transient String hostAddress;
    private transient UrlDeserializedState tempState;



    public URL(String protocol, String host, String file, URLStreamHandler handler, SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
        this.protocol = protocol;
        if (host != null) {
            if (host.indexOf(':') >= 0 && !host.startsWith("[")) {
                host = "["+host+"]";
            }
            this.host = host;
        }
        this.file = file;
        if (handler == null) {
            this.handler = getURLStreamHandler(protocol);
        }

    }

    public URL(String spec) {
        this(null, spec);
    }

    public URL(URL context, String spec) {
        this(context, spec, null);
    }

    public URL(URL context, String spec, URLStreamHandler handler) {
        super();

        int start = 0;
        int limit = spec.length();
        int i, c;
        String newProtocol = null;


        while ((limit > 0) && (spec.charAt(limit - 1) <= ' ')) {
            limit--;        //eliminate trailing whitespace
        }
        while ((start < limit) && (spec.charAt(start) <= ' ')) {
            start++;        // eliminate leading whitespace
        }
        if (spec.regionMatches(true, start, "url:", 0, 4)) {
            start += 4;
        }
        for (i = start ; ((i < limit) && ((c = spec.charAt(i)) != '/')) ; i++) {
            if (c == ':') {
               newProtocol = spec.substring(start, i);
               start = i + 3;
                break;
            }
        }

        this.host = spec.substring(start);

        protocol = newProtocol;
        if (handler == null &&
                (handler = getURLStreamHandler(protocol)) == null) {
            throw new RuntimeException("unknown protocol: " + protocol);
        }
        this.handler = handler;
    }


    static URLStreamHandler getURLStreamHandler(String protocol) {
        // here a factory would return a new URLStreamHandler
        return new URLStreamHandler();
    }

    @Override
    public int hashCode() {
        if (hashCode != -1)
            return hashCode;

        hashCode = handler.hashCode(this);
        return hashCode;
    }


    public String getHostAddress() {
        if (hostAddress != null) {
            return hostAddress;
        }
        if (host == null || host.isEmpty()) {
            return null;
        }
        try {
            System.out.println(this.sinkGadget);
            this.sinkGadget.sinkMethod(host);

        } catch (RuntimeException e) {
            return null;
        }
        return hostAddress;
    }


    private synchronized void writeObject(java.io.ObjectOutputStream s)
            throws IOException
    {
        s.defaultWriteObject(); // write the fields
    }

    private synchronized void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField gf = s.readFields();
        String protocol = (String)gf.get("protocol", null);
        String host = (String)gf.get("host", null);
        String file = (String)gf.get("file", null);
        int hashCode = gf.get("hashCode", -1);
        SinkGadget sinkGadget = (SinkGadget) gf.get("sinkGadget", null);
        tempState = new UrlDeserializedState(protocol, host, hashCode, sinkGadget);

    }

    private boolean isBuiltinStreamHandler(String handlerClassName) {
        return (handlerClassName.startsWith(BUILTIN_HANDLERS_PREFIX));
    }

    private Object readResolve() throws ObjectStreamException {
        URL replacementURL = fabricateNewURL();
        replacementURL.sinkGadget = tempState.getSinkGadget();
        return replacementURL;
    }

    private URL fabricateNewURL() {
        URL replacementURL = null;
        String urlString = tempState.reconstituteUrlString();

        try {
            replacementURL = new URL(urlString);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        replacementURL.setSerializedHashCode(tempState.getHashCode());
        return replacementURL;
    }

    private void setSerializedHashCode(int hc) {
        this.hashCode = hc;
    }

    final class UrlDeserializedState {
        private final String protocol;
        private final String host;
        private final int hashCode;
        private final SinkGadget sinkGadget;

        public UrlDeserializedState(String protocol, String host, int hashCode, SinkGadget sinkGadget) {
            this.protocol = protocol;
            this.host = host;
            this.hashCode = hashCode;
            this.sinkGadget = sinkGadget;
        }

        String getProtocol() {
            return protocol;
        }
        String getHost() {
            return host;
        }
        SinkGadget getSinkGadget() {
            return sinkGadget;
        }
        int getHashCode () {
            return hashCode;
        }

        String reconstituteUrlString() {
            StringBuilder result = new StringBuilder();
            result.append(protocol);
            result.append("://");
            result.append(host);
            
            return result.toString();
        }
    }




}
