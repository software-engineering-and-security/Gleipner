package gleipner.chains.polymorphism;

public class Polymorphism_001_LinkGadget extends Polymorphism_001_Parent {
    public Polymorphism_001_SinkGadget sinkGadget;

    public Polymorphism_001_LinkGadget () {
    }

    public Polymorphism_001_LinkGadget (Polymorphism_001_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}