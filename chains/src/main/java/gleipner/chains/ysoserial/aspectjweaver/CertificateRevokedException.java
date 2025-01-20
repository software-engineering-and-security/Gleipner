package gleipner.chains.ysoserial.aspectjweaver;

import gleipner.core.annotations.FalsePositive;
import gleipner.core.annotations.InterProcedural;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CertificateRevokedException implements Serializable {

    private transient Map<String, MockExtension> extensions;

    private void writeObject(ObjectOutputStream oos) throws IOException {
        // Write out the non-transient fields
        // (revocationDate, reason, authority)
        oos.defaultWriteObject();

        // Write out the size (number of mappings) of the extensions map
        oos.writeInt(extensions.size());

        // For each extension in the map, the following are emitted (in order):
        // the OID String (Object), the criticality flag (boolean), the length
        // of the encoded extension value byte array (int), and the encoded
        // extension value byte array. The extensions themselves are emitted
        // in no particular order.
        for (Map.Entry<String, MockExtension> entry : extensions.entrySet()) {
            MockExtension ext = entry.getValue();
            oos.writeObject(ext.getId());
            oos.writeBoolean(ext.isCritical());
            byte[] extVal = ext.getValue();
            oos.writeInt(extVal.length);
            oos.write(extVal);
        }
    }

    @FalsePositive
    @InterProcedural(InterProcedural.READ_OBJECT)
    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        // Read in the non-transient fields
        // (revocationDate, reason, authority)
        ois.defaultReadObject();

        // Read in the size (number of mappings) of the extensions map
        // and create the extensions map
        int size = ois.readInt();
        if (size == 0) {
            extensions = Collections.emptyMap();
        } else {
            extensions = new HashMap<String, MockExtension>(size);
        }

        // Read in the extensions and put the mappings in the extensions map
        for (int i = 0; i < size; i++) {
            String oid = (String) ois.readObject();
            boolean critical = ois.readBoolean();
            int length = ois.readInt();
            byte[] extVal = new byte[length];
            ois.readFully(extVal);
            MockExtension ext = new MockExtension(critical, extVal);
            extensions.put(oid, ext);
        }
    }


}
