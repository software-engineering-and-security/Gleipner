package gleipner.chains.polymorphism;

public class Polymorphism_018_LinkGadget extends Polymorphism_018_Child508 {
    public Polymorphism_018_SinkGadget sinkGadget;

    public Polymorphism_018_LinkGadget () {
    }

    public Polymorphism_018_LinkGadget (Polymorphism_018_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}