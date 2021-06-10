package app.domain.model;

public class TestParameter {

    private String code;

    public TestParameter(String code){
        this.code = code;
    }

    public TestParameterResult addResult(double result, String metric, ReferenceValue referenceValue){
        return new TestParameterResult(result, metric, referenceValue);
    }
}
