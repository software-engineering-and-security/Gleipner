package gleipner.chains.polymorphism;

public class Polymorphism_012_LinkGadget extends Polymorphism_012_Child4 {
    public Polymorphism_012_SinkGadget sinkGadget;

    public Polymorphism_012_LinkGadget () {
    }

    public Polymorphism_012_LinkGadget (Polymorphism_012_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}