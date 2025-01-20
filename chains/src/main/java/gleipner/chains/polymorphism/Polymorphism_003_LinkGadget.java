package gleipner.chains.polymorphism;

public class Polymorphism_003_LinkGadget extends Polymorphism_003_Child36 {
    public Polymorphism_003_SinkGadget sinkGadget;

    public Polymorphism_003_LinkGadget () {
    }

    public Polymorphism_003_LinkGadget (Polymorphism_003_SinkGadget sinkGadget) {
        this.sinkGadget = sinkGadget;
    }

    @Override
    public void linkMethod() {
        super.linkMethod();
        sinkGadget.linkMethod();
    }
}