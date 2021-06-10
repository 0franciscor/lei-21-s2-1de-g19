package app.domain.model;

import com.example1.ExternalModule3API;

public class ExternalModuleBloodWithKey extends ExternalModule {

    ExternalModule3API externalModule3API;

    public ExternalModuleBloodWithKey(){
        externalModule3API = new ExternalModule3API();
    }

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {

        String parameterID = parameter.getCode();
        double minReferenceValue = externalModule3API.getMinReferenceValue(parameter.getCode(), 12345);
        double maxReferenceValue = externalModule3API.getMaxReferenceValue(parameter.getCode(), 12345);
        String metric = externalModule3API.usedMetric(parameter.getCode(), 12345);

        return new ReferenceValue(parameterID, minReferenceValue, maxReferenceValue, metric);
    }

    @Override
    public String toString(){
        return String.format("External Module Blood (with key).");
    }
}
