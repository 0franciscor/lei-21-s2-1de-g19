package app.domain.model;

import com.example3.CovidReferenceValues1API;

public class ExternalModuleCovid extends ExternalModule {

    CovidReferenceValues1API covidReferenceValues1API;

    public ExternalModuleCovid(){
        covidReferenceValues1API = new CovidReferenceValues1API();
    }

    @Override
    public ReferenceValue getReferenceValue(TestParameter testParameter) {
        String parameterID = testParameter.getCode();
        double minReferenceValue = covidReferenceValues1API.getMinReferenceValue(testParameter.getCode(), 12345);
        double maxReferenceValue = covidReferenceValues1API.getMaxReferenceValue(testParameter.getCode(), 12345);
        String metric = covidReferenceValues1API.usedMetric(testParameter.getCode(), 12345);

        return new ReferenceValue(parameterID, minReferenceValue, maxReferenceValue, metric);
    }

    @Override
    public String toString(){
        return String.format("External Module Covid (with key).");
    }
}
