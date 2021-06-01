package app.domain.model;

import com.example3.CovidReferenceValues1API;

public class ExternalModuleCovid extends ExternalModule {

    CovidReferenceValues1API em1;
    ReferenceValue refValue;
    String metrics;
    public ExternalModuleCovid(){
        em1 = new CovidReferenceValues1API();
    }

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        refValue.setMinValue(em1.getMinReferenceValue(parameter.getCode(), 12345));
        refValue.setMaxValue(em1.getMaxReferenceValue(parameter.getCode(), 12345));
        return refValue;
    }
    public String getMetrics (Parameter parameter) {
        metrics = em1.usedMetric(parameter.getCode(), 12345);
        return metrics;
    }

    @Override
    public String toString(){
        return String.format("External Module Covid (with key).");
    }
}
