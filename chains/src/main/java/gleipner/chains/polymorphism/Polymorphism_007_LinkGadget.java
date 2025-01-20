package gleipner.chains.polymorphism;

public class Polymorphism_007_LinkGadget extends Polymorphism_007_Child80 {
    public Polymorphism_007_SinkGadget sinkGadget;

    public Polymorphism_007_LinkGadget () {
    }

    public Polymorphism_007_LinkGadget (Polymorphism_007_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}