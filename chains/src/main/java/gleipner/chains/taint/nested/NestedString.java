package gleipner.chains.taint.nested;

public class NestedString extends Nested{

    private String value;

    public NestedString(String value, int size) {
        super(size);
        this.value = value;
    }

    public NestedString(String value) {
        super(0);
        this.value = value;
    }
    @Override
    public String invoke(String arg) {
        return value;
    }
}
