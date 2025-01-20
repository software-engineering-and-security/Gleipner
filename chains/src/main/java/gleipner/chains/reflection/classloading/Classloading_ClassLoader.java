package gleipner.chains.reflection.classloading;

import java.util.Base64;

public class Classloading_ClassLoader extends ClassLoader{

    //Below is the b64 String of the compiled class "LoadedClass"
    /*
    package src.main.java;
    public class LoadedClass {
        public static final boolean FLAG = true;
    }
     */
    private static final String b64Class = "yv66vgAAADQAFAoAAwARBwASBwATAQAERkxBRwEAAVoBAA1Db25zdGFudFZhbHVlAwAAAAEBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAG0xzcmMvbWFpbi9qYXZhL0xvYWRlZENsYXNzOwEAClNvdXJjZUZpbGUBABBMb2FkZWRDbGFzcy5qYXZhDAAIAAkBABlzcmMvbWFpbi9qYXZhL0xvYWRlZENsYXNzAQAQamF2YS9sYW5nL09iamVjdAAhAAIAAwAAAAEAGQAEAAUAAQAGAAAAAgAHAAEAAQAIAAkAAQAKAAAALwABAAEAAAAFKrcAAbEAAAACAAsAAAAGAAEAAAADAAwAAAAMAAEAAAAFAA0ADgAAAAEADwAAAAIAEA==";

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = Base64.getDecoder().decode(b64Class);

        return defineClass(name, classBytes, 0, classBytes.length);
    }
}
