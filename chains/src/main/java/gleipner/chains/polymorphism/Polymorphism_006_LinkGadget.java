package gleipner.chains.polymorphism;

public class Polymorphism_006_LinkGadget extends Polymorphism_006_Child16 {
    public Polymorphism_006_SinkGadget sinkGadget;

    public Polymorphism_006_LinkGadget () {
    }

    public Polymorphism_006_LinkGadget (Polymorphism_006_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}