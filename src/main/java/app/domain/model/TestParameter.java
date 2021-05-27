package app.domain.model;

import java.util.Date;

public class TestParameter {

    private Parameter parameter;
    private TestParameterResult tpr;

    public TestParameter(Parameter parameter, TestParameterResult tpr){
        this.parameter = parameter;
        this.tpr = tpr;
    }

    public Parameter getParameter(){
        return this.parameter;
    }

    public void addResult(Date registrationDateTime, String result, String metric, ReferenceValue refValue){
        tpr.setRegistrationDateTime(registrationDateTime);
        tpr.setResult(result);
        tpr.setMetric(metric);
        tpr.setRefValue(refValue);
    }

}
