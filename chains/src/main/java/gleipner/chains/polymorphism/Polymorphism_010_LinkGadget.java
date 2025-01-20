package gleipner.chains.polymorphism;

public class Polymorphism_010_LinkGadget extends Polymorphism_010_Parent {
    public Polymorphism_010_SinkGadget sinkGadget;

    public Polymorphism_010_LinkGadget () {
    }

    public Polymorphism_010_LinkGadget (Polymorphism_010_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}