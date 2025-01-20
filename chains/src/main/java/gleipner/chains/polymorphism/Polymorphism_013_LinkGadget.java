package gleipner.chains.polymorphism;

public class Polymorphism_013_LinkGadget extends Polymorphism_013_Child12 {
    public Polymorphism_013_SinkGadget sinkGadget;

    public Polymorphism_013_LinkGadget () {
    }

    public Polymorphism_013_LinkGadget (Polymorphism_013_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}