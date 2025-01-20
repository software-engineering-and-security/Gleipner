package gleipner.chains.ysoserial.c3p0;

import gleipner.core.Controller;
import gleipner.core.SinkGadget;

import javax.naming.*;
import java.io.IOException;
import java.util.Hashtable;

public class ReferenceIndirector {

    Name name;
    Name contextName;
    Hashtable environmentProperties;

    public ReferenceIndirector() {

    }
    public IndirectlySerialized indirectForm(Object referencable) throws Exception {
        Reference ref = ((Referenceable)referencable).getReference();
        return new ReferenceSerialized(ref, this.name, this.contextName, this.environmentProperties);
    }

    private static class ReferenceSerialized implements IndirectlySerialized {
        Reference reference;
        Name name;
        Name contextName;
        Hashtable env;
        ReferenceSerialized(Reference reference, Name name, Name contextName, Hashtable env) {
            this.reference = reference;
            this.name = name;
            this.contextName = contextName;
            this.env = env;
        }

        public Object getObject() throws ClassNotFoundException, IOException {
            try {
                InitialContext initialContext;
                if (this.env == null) {
                    initialContext = new InitialContext();
                } else {
                    initialContext = new InitialContext(this.env);
                }

                Context context = null;
                if (this.contextName != null) {
                    context = (Context)initialContext.lookup(this.contextName);
                }

                ReferenceableUtils.referenceToObject(this.reference);
            } catch (RuntimeException e) {
                throw new RuntimeException();
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}
