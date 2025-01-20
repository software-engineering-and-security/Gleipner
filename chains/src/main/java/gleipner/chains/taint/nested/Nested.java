package gleipner.chains.taint.nested;

import java.io.Serializable;

public class Nested implements Serializable {

    protected Nested[] nArray;

    public Nested(int size) {
        nArray = new Nested[size];
    }

    public Nested(Nested ... nItems) {
        nArray = nItems;
    }

    public Nested get(int i) {return this.nArray[i];}
    public Nested set(int i, Nested n) {
        return this.nArray[i] = n;
    }
    public String invoke(String arg) { return null;};

}
