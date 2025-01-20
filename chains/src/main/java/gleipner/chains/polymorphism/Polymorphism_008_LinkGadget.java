package gleipner.chains.polymorphism;

public class Polymorphism_008_LinkGadget extends Polymorphism_008_Parent {
    public Polymorphism_008_SinkGadget sinkGadget;

    public Polymorphism_008_LinkGadget () {
    }

    public Polymorphism_008_LinkGadget (Polymorphism_008_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}