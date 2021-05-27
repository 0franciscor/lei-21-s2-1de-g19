package auth.mappers.dto;

import app.domain.model.Parameter;
import app.domain.model.ReferenceValue;
import app.domain.model.ParameterResult;

import java.util.Date;

public class TestParameterDto {

    private Parameter parameter;
    private ParameterResult tpr;

    public TestParameterDto(Parameter parameter, ParameterResult tpr){
        this.parameter = parameter;
        this.tpr = tpr;
    }

    public Parameter getParameter(){
        return this.parameter;
    }

    public ParameterResult getTestParameterResult(){
        return this.tpr;
    }

    public void addResult(Date registrationDateTime, String result, String metric, ReferenceValue refValue){
        tpr.setRegistrationDateTime(registrationDateTime);
        tpr.setResult(result);
        tpr.setMetric(metric);
        tpr.setRefValue(refValue);
    }
}
