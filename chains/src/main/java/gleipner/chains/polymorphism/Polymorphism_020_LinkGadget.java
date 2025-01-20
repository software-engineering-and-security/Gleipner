package gleipner.chains.polymorphism;

public class Polymorphism_020_LinkGadget extends Polymorphism_020_Child2044 {
    public Polymorphism_020_SinkGadget sinkGadget;

    public Polymorphism_020_LinkGadget () {
    }

    public Polymorphism_020_LinkGadget (Polymorphism_020_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}