package gleipner.chains.ysoserial.aspectjweaver;

import gleipner.core.SinkGadget;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class SimpleCache {

    private static final String SAME_BYTES_STRING = "IDEM";
    private static final byte[] SAME_BYTES = SAME_BYTES_STRING.getBytes();

    private static class StoreableCachingMap extends HashMap {

        private String folder;
        private int storingTimer;
        private static final String CACHENAMEIDX = "cache.idx";
        private SinkGadget sinkGadget;

        private StoreableCachingMap(String folder, int storingTimer, SinkGadget sinkGadget){
            this.folder = folder;
            this.storingTimer = storingTimer;
            this.sinkGadget = sinkGadget;
        }

        @Override
        public Object put(Object key, Object value) {
            try {
                String path = null;

                if (Arrays.equals(value.toString().getBytes(), SAME_BYTES)) {

                } else {
                    path = writeToPath(key, value.toString());
                }
                Object result = super.put(key, path);
                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private String writeToPath(Object key, String value) throws IOException{
            String fullPath = folder + File.separator + key;
            try {
                this.sinkGadget.sinkMethod(value);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            return fullPath;
        }

    }


}
