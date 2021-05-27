package app.domain.model;

import java.util.Date;

public class TestParameterResult {

    private Date registrationDateTime;
    private String result;
    private String metric;
    private ReferenceValue refValue;

    public TestParameterResult(Date registrationDateTime, String result, String metric, ReferenceValue refValue){
        this.registrationDateTime = registrationDateTime;
        this.result = result;
        this.metric = metric;
        this.refValue = refValue;
    }

    public void setRegistrationDateTime(Date registrationDateTime){
        this.registrationDateTime = registrationDateTime;
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
}
