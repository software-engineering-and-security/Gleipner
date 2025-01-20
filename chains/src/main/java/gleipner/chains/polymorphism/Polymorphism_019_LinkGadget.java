package gleipner.chains.polymorphism;

public class Polymorphism_019_LinkGadget extends Polymorphism_019_Child1020 {
    public Polymorphism_019_SinkGadget sinkGadget;

    public Polymorphism_019_LinkGadget () {
    }

    public Polymorphism_019_LinkGadget (Polymorphism_019_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}