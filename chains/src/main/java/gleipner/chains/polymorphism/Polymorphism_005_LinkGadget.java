package gleipner.chains.polymorphism;

public class Polymorphism_005_LinkGadget extends Polymorphism_005_Parent {
    public Polymorphism_005_SinkGadget sinkGadget;

    public Polymorphism_005_LinkGadget () {
    }

    public Polymorphism_005_LinkGadget (Polymorphism_005_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}