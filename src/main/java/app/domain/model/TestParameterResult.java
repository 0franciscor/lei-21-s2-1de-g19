package app.domain.model;

/**
 * The test parameter result, which contains the result of a certain parameter.
 *
 * @author Francisco Redol (1201239) & Eduardo Gonçalves (1201238)
 */
public class TestParameterResult {

    /**
     * The Parameter Result.
     */
    private double result;

    /**
     * The Parameter Metric.
     */
    private String metric;

    /**
     * The Reference Value object which is correspondent to the Parameter.
     */
    private ReferenceValue referenceValue;

    /**
     * @param result
     * @param metric
     * @param referenceValue
     *
     * The TestParameterResult constructor, which creates a TestParameterResult object.
     */
    public TestParameterResult(double result, String metric, ReferenceValue referenceValue){
        this.result = result;
        this.metric = metric;
        this.referenceValue = referenceValue;
    }

    /**
     * @return the result value.
     */
    public double getResult(){
        return this.result;
    }

    /**
     * @return the metric.
     */
    public String getMetric(){
        return this.metric;
    }

    /**
     * @return the correspondent ReferenceValue object.
     */
    public ReferenceValue getReferenceValue(){
        return this.referenceValue;
    }

}
