package app.domain.shared;

import app.domain.model.ReferenceValue;
import app.domain.model.TestParameter;
import com.example1.ExternalModule3API;

/**
 * The ExternalModuleBloodWithKey, which is responsible for interacting with the ExternalModule3API.
 *
 * @author Francisco Redol
 */
public class ExternalModuleBloodWithKey extends ExternalModule {

    /**
     * The API.
     */
    ExternalModule3API externalModule3API;

    /**
     * The Constructor, which is responsible for creating a External Module Object.
     */
    public ExternalModuleBloodWithKey(){
        externalModule3API = new ExternalModule3API();
    }

    /**
     * @param testParameter the TestParameter
     * @return a Reference Value corresponding to that API.
     */
    @Override
    public ReferenceValue getReferenceValue(TestParameter testParameter) {

        String parameterID = testParameter.getCode();
        double minReferenceValue = externalModule3API.getMinReferenceValue(testParameter.getCode(), 12345);
        double maxReferenceValue = externalModule3API.getMaxReferenceValue(testParameter.getCode(), 12345);
        String metric = externalModule3API.usedMetric(testParameter.getCode(), 12345);

        return new ReferenceValue(parameterID, minReferenceValue, maxReferenceValue, metric);
    }

    /**
     * @param ParameterID that identifies a certain Parameter.
     * @return the used metric
     */
    @Override
    public String getMetric(String ParameterID){
       return externalModule3API.usedMetric(ParameterID, 12345);
    }

    /**
     * @return a String containing information about the module.
     */
    @Override
    public String toString(){
        return "External Module Blood (with key).";
    }
}
