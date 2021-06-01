package app.domain.model;

import java.util.Date;

public class ParameterResult {

    private Parameter parameter;
    private Date resultRegistrationDateTime;
    private String result;
    private String metric;
    private ReferenceValue refValue;


    public ParameterResult(Test test, Parameter parameter, String result, String metric){
        this.parameter = parameter;
        this.resultRegistrationDateTime = new Date();
        this.result = result;
        ExternalModule em = test.getTestType().getExternalModule();
        refValue = em.getReferenceValue(parameter);
        this.metric = em.getMetrics(parameter);

    }

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

    public void addResult(String result){
        this.setResult(result);
    }
    public String toString(int opt) {
        if (opt == 1)
            return String.format("Reference Value: %s Result: %s", refValue, result);
        if (opt == 2)
            return String.format("Parameter: %s Metric used: %s Reference Value: %s",parameter.getDesignation(), metric, refValue);
        return null;
    }
}
