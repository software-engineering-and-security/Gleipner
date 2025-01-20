package gleipner.chains.polymorphism;

public class Polymorphism_014_LinkGadget extends Polymorphism_014_Child28 {
    public Polymorphism_014_SinkGadget sinkGadget;

    public Polymorphism_014_LinkGadget () {
    }

    public Polymorphism_014_LinkGadget (Polymorphism_014_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}