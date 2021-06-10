package app.domain.model;

/**
 * The TestParameter Class
 *
 * @author Francisco Redol
 */
public class TestParameter {

    /**
     * The parameter's code.
     */
    private String code;

    /**
     * The TestParameterResult class.
     */
    private TestParameterResult testParameterResult;

    /**
     * @param code that identifies a Parameter.
     *
     * The TestParameter Constructor.
     */
    public TestParameter(String code){
        this.code = code;
    }

    /**
     * @param result
     * @param metric
     * @param referenceValue
     *
     * Method responsible for creating a TestParameterResult Class.
     *
     * @return success of the operation
     */
    public boolean addResult(double result, String metric, ReferenceValue referenceValue){
        this.testParameterResult = new TestParameterResult(result, metric, referenceValue);
        return true;
    }

    /**
     * @return the Parameter code.
     */
    public String getCode(){
        return this.code;
    }

    /**
     * @return the Parameter Test Result.
     */
    public TestParameterResult getTestParameterResult(){
        return this.testParameterResult;
    }

    /**
     * @return a String containing a TestParameter info.
     */
    @Override
    public String toString(){
        return String.format("ParameterID= %s, metric: %s, minValue: %s, maxValue: %s, and the result is %s", this.code, testParameterResult.getMetric(), testParameterResult.getReferenceValue().getMinValue(), testParameterResult.getReferenceValue().getMaxValue(), testParameterResult.getResult());
    }
}
