package app.domain.shared;

import app.domain.model.ReferenceValue;
import app.domain.model.TestParameter;
import com.example3.CovidReferenceValues1API;

/**
 * The CovidReferenceValues1API, which is responsible for interacting with the CovidReferenceValues1API.
 *
 * @author Francisco Redol
 */
public class ExternalModuleCovid extends ExternalModule {

    /**
     * The API.
     */
    CovidReferenceValues1API covidReferenceValues1API;

    /**
     * The Constructor, which is responsible for creating a External Module Object.
     */
    public ExternalModuleCovid(){
        covidReferenceValues1API = new CovidReferenceValues1API();
    }

    /**
     * @param testParameter the TestParameter
     * @return a Reference Value corresponding to that API.
     */
    @Override
    public ReferenceValue getReferenceValue(TestParameter testParameter) {
        String parameterID = testParameter.getCode();
        double minReferenceValue = covidReferenceValues1API.getMinReferenceValue(testParameter.getCode(), 12345);
        double maxReferenceValue = covidReferenceValues1API.getMaxReferenceValue(testParameter.getCode(), 12345);
        String metric = covidReferenceValues1API.usedMetric(testParameter.getCode(), 12345);

        return new ReferenceValue(parameterID, minReferenceValue, maxReferenceValue, metric);
    }

    /**
     * @param ParameterID that identifies a certain Parameter.
     * @return the used metric
     */
    @Override
    public String getMetric(String ParameterID){
        return covidReferenceValues1API.usedMetric(ParameterID, 12345);
    }

    /**
     * @return a String containing information about the module.
     */
    @Override
    public String toString(){
        return "External Module Covid (with key).";
    }
}
