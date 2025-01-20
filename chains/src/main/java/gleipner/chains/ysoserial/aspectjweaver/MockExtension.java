package gleipner.chains.ysoserial.aspectjweaver;

public class MockExtension {
    private boolean critical;
    private byte[] value;

    public MockExtension(boolean critical, byte[] extValue) {
        this.critical = critical;
        this.value = extValue.clone();
    }

    public boolean isCritical() {
        return critical;
    }

    public byte[] getValue() {
        return null;
    }

    public String getId() {
        return "whatever";
    }


}
