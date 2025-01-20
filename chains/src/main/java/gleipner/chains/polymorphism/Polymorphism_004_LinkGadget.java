package gleipner.chains.polymorphism;

public class Polymorphism_004_LinkGadget extends Polymorphism_004_Child117 {
    public Polymorphism_004_SinkGadget sinkGadget;

    public Polymorphism_004_LinkGadget () {
    }

    public Polymorphism_004_LinkGadget (Polymorphism_004_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}