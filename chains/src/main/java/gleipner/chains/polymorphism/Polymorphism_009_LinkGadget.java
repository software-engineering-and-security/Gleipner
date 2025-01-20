package gleipner.chains.polymorphism;

public class Polymorphism_009_LinkGadget extends Polymorphism_009_Child25 {
    public Polymorphism_009_SinkGadget sinkGadget;

    public Polymorphism_009_LinkGadget () {
    }

    public Polymorphism_009_LinkGadget (Polymorphism_009_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}