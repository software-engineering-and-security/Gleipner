package gleipner.chains.polymorphism;

public class Polymorphism_002_LinkGadget extends Polymorphism_002_Child9 {
    public Polymorphism_002_SinkGadget sinkGadget;

    public Polymorphism_002_LinkGadget () {
    }

    public Polymorphism_002_LinkGadget (Polymorphism_002_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}