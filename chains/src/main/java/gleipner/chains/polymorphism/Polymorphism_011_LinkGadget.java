package gleipner.chains.polymorphism;

public class Polymorphism_011_LinkGadget extends Polymorphism_011_Parent {
    public Polymorphism_011_SinkGadget sinkGadget;

    public Polymorphism_011_LinkGadget () {
    }

    public Polymorphism_011_LinkGadget (Polymorphism_011_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}