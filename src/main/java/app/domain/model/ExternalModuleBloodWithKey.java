package app.domain.model;

import com.example1.ExternalModule3API;

public class ExternalModuleBloodWithKey extends ExternalModule {

    ExternalModule3API em3;
    ReferenceValue refValue;
    String metrics;

    public ExternalModuleBloodWithKey(){
        em3 = new ExternalModule3API();
    }

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        refValue.setMinValue(em3.getMinReferenceValue(parameter.getCode(), 12345));
        refValue.setMaxValue(em3.getMaxReferenceValue(parameter.getCode(), 12345));
        return refValue;
    }
    public String getMetrics (Parameter parameter) {
        metrics = em3.usedMetric(parameter.getCode(), 12345);
        return metrics;
    }

    @Override
    public String toString(){
        return String.format("External Module Blood (with key).");
    }
}
