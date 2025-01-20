package gleipner.chains.reflection.classloading;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Classloading_ClassLoader_003 extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String filename = name.split("\\.")[name.split("\\.").length - 1];

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                filename.replace('.', File.separatorChar) + ".class");

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;

        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] b = byteStream.toByteArray();

        return defineClass(name, b, 0, b.length);
    }
}
