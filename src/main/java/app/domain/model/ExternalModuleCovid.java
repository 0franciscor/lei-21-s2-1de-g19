package app.domain.model;

import com.example3.CovidReferenceValues1API;

public class ExternalModuleCovid extends ExternalModule {

    CovidReferenceValues1API em1;
    ReferenceValue refValue;

    public ExternalModuleCovid(){
        em1 = new CovidReferenceValues1API();
    }

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        refValue.setMinValue(em1.getMinReferenceValue(parameter.getCode(), 12345));
        refValue.setMaxValue(em1.getMaxReferenceValue(parameter.getCode(), 12345));
        return refValue;
    }

    @Override
    public String toString(){
        return String.format("External Module Covid (with key).");
    }
}
