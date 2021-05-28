package app.domain.model;

import java.util.Date;

public class ParameterResult {

    private Parameter parameter;
    private Date resultRegistrationDateTime;
    private String result;
    private String metric;
    private ReferenceValue refValue;


    /*public ParameterResult(Test test, Parameter parameter, String result, String metric){
        this.parameter = parameter;
        this.resultRegistrationDateTime = new Date();
        this.result = result;
        ExternalModule em = test.getTestType().getExternalModule();
        refValue = em.getReferenceValue(parameter);
        this.metric = em.getMetrics(parameter);

    }*/

    public void setRegistrationDateTime(Date registrationDateTime){
        this.resultRegistrationDateTime = registrationDateTime;
    }

    public void setResult(String result){
        this.result = result;
    }

    public void setMetric(String metric){
        this.metric = metric;
    }

    public void setRefValue(ReferenceValue refValue){
        this.refValue = refValue;
    }

    public Parameter getParameter(){
        return this.parameter;
    }

    public void addResult(Date registrationDateTime, String result, String metric, ReferenceValue refValue){
        this.setRegistrationDateTime(registrationDateTime);
        this.setResult(result);
        this.setMetric(metric);
        this.setRefValue(refValue);
    }
    public String toString() {
        return String.format("Reference Value: %s Result: %s", refValue, result);
    }
}
