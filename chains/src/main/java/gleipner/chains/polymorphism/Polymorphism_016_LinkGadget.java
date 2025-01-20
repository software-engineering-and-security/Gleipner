package gleipner.chains.polymorphism;

public class Polymorphism_016_LinkGadget extends Polymorphism_016_Child124 {
    public Polymorphism_016_SinkGadget sinkGadget;

    public Polymorphism_016_LinkGadget () {
    }

    public Polymorphism_016_LinkGadget (Polymorphism_016_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}