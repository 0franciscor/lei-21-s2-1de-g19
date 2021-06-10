package app.domain.model;

import com.example1.ExternalModule3API;

public class ExternalModuleBloodWithKey extends ExternalModule {

    ExternalModule3API externalModule3API;

    public ExternalModuleBloodWithKey(){
        externalModule3API = new ExternalModule3API();
    }

    @Override
    public ReferenceValue getReferenceValue(TestParameter testParameter) {

        String parameterID = testParameter.getCode();
        double minReferenceValue = externalModule3API.getMinReferenceValue(testParameter.getCode(), 12345);
        double maxReferenceValue = externalModule3API.getMaxReferenceValue(testParameter.getCode(), 12345);
        String metric = externalModule3API.usedMetric(testParameter.getCode(), 12345);

        return new ReferenceValue(parameterID, minReferenceValue, maxReferenceValue, metric);
    }

    @Override
    public String toString(){
        return String.format("External Module Blood (with key).");
    }
}
