package gleipner.chains.polymorphism;

public class Polymorphism_015_LinkGadget extends Polymorphism_015_Child60 {
    public Polymorphism_015_SinkGadget sinkGadget;

    public Polymorphism_015_LinkGadget () {
    }

    public Polymorphism_015_LinkGadget (Polymorphism_015_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}