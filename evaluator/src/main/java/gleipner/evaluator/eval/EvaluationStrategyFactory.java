package gleipner.evaluator.eval;

public class EvaluationStrategyFactory {

    public static final String GADGET_INSPECTOR = "gadgetinspector";
    public static final String BASIC = "basic";
    public static final String SERIANALYZER = "serianalyzer";
    public static final String SERHYBRID = "serhybrid";
    public static final String JCHAINZ = "jchainz";
    public static final String TABBY = "tabby";
    public static final String CRYSTALLIZER = "crystallizer";

    public static EvaluationStrategy getInstance() {
        return new BasicEvaluationStrategy();
    }

    public static EvaluationStrategy getInstance(String name) {
        if (name.equals(GADGET_INSPECTOR)) {
            return new GadgetInspectorEvaluationStrategy();
        } else if (name.equals(BASIC)) {
            return new BasicEvaluationStrategy();
        } else if (name.equals(SERIANALYZER)) {
            return new SerianalyzerEvaluationStrategy();
        } else if (name.equals(SERHYBRID)) {
            return new SerHybridEvaluationStrategy();
        } else if (name.equals(JCHAINZ)) {
            return new JChainzEvaluationStrategy();
        } else if (name.equals(TABBY)) {
            return new TabbyEvaluationStrategy();
        }

        return null;
    }

}
