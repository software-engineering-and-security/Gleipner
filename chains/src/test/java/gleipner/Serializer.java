package gleipner;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Serializer {

    public static void serializeDeserialize(Object o) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {

        }

    }

    public static void serializeToFile(Object o, String file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deserializeFromFile(Object o, String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void externalize(Externalizable o) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            o.writeExternal(oos);
            oos.flush();
            oos.close();

            byte[] outBytes = bos.toByteArray();
            bos.close();

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(outBytes));
            Externalizable o2 = o.getClass().getDeclaredConstructor().newInstance();
            o2.readExternal(ois);

        } catch (IOException | ClassNotFoundException ignored) {

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
