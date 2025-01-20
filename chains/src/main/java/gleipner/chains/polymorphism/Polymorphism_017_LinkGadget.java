package gleipner.chains.polymorphism;

public class Polymorphism_017_LinkGadget extends Polymorphism_017_Child252 {
    public Polymorphism_017_SinkGadget sinkGadget;

    public Polymorphism_017_LinkGadget () {
    }

    public Polymorphism_017_LinkGadget (Polymorphism_017_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}