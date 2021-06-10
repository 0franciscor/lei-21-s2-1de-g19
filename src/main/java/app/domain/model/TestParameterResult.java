package app.domain.model;

/**
 * The test parameter result, which contains the result of a certain parameter.
 *
 * @author Francisco Redol (1201239) & Eduardo Gonçalves (1201238)
 */
public class TestParameterResult {

    private double result;

    private String metric;

    private ReferenceValue referenceValue;

    public TestParameterResult(double result, String metric, ReferenceValue referenceValue){
        this.result = result;
        this.metric = metric;
        this.referenceValue = referenceValue;
    }

    public double getResult(){
        return this.result;
    }

    public String getMetric(){
        return this.metric;
    }

    public ReferenceValue getReferenceValue(){
        return this.referenceValue;
    }
}
