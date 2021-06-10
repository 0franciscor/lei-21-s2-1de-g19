package app.domain.model;

public class TestParameter {

    private String code;

    private TestParameterResult testParameterResult;

    public TestParameter(String code){
        this.code = code;
    }

    public boolean addResult(double result, String metric, ReferenceValue referenceValue){
        this.testParameterResult = new TestParameterResult(result, metric, referenceValue);
        return true;
    }

    public String getCode(){
        return this.code;
    }

    public TestParameterResult getTestParameterResult(){
        return this.testParameterResult;
    }
}
