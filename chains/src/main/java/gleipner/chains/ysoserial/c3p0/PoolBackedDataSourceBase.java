package gleipner.chains.ysoserial.c3p0;

import gleipner.core.SinkGadget;

import javax.sql.ConnectionPoolDataSource;
import java.io.*;

public class PoolBackedDataSourceBase implements Serializable {

    private static final short VERSION = 1;
    private SinkGadget sinkGadget;
    private ConnectionPoolDataSource connectionPoolDataSource;

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeShort(1);
        oos.writeObject(this.sinkGadget);

        try {
            oos.writeObject(this.connectionPoolDataSource);
        } catch (NotSerializableException e) {
            ReferenceIndirector indirector;
            try {
                indirector = new ReferenceIndirector();
                // here is the trick ..., we can create our own subclass of ConnectionPoolDataSource on the attacker side, and the client doesn't even have to include it on its classpath
                // on the client side all that appears is a IndirectlySerialized object
                oos.writeObject(indirector.indirectForm(this.connectionPoolDataSource));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        short version = ois.readShort();
        ReferenceableUtils.sinkGadget = (SinkGadget) ois.readObject();
        switch (version) {
            case 1:
                Object o = ois.readObject();
                if (o instanceof IndirectlySerialized) {
                    o = ((IndirectlySerialized)o).getObject();
                }
                return;
            default:
                throw new IOException("Unsupported Serialized Version: " + version);
        }
    }

}
