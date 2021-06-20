package app.domain.shared;

import app.domain.model.ReferenceValue;
import app.domain.model.TestParameter;
import com.example2.EMRefValue;
import com.example2.ExternalModule2API;

/**
 * The ExternalModuleBloodWithoutKey, which is responsible for interacting with the ExternalModule2API.
 *
 * @author Francisco Redol
 */
public class ExternalModuleBloodWithoutKey extends ExternalModule {

    /**
     * The API.
     */
    ExternalModule2API externalModule2API;

    /**
     * The Constructor, which is responsible for creating a External Module Object.
     */
    public ExternalModuleBloodWithoutKey(){
        externalModule2API = new ExternalModule2API();
    }

    /**
     * @param testParameter the TestParameter
     * @return a Reference Value corresponding to that API.
     */
    @Override
    public ReferenceValue getReferenceValue(TestParameter testParameter) {
        EMRefValue ReferenceValue = externalModule2API.getReferenceFor(testParameter.getCode());
        return new ReferenceValue(ReferenceValue);
    }

    /**
     * @param ParameterID that identifies a certain Parameter.
     * @return the used metric
     */
    @Override
    public String getMetric(String ParameterID){
        return externalModule2API.getMetricsFor(ParameterID);
    }

    /**
     * @return a String containing information about the module.
     */
    @Override
    public String toString(){
        return "External Module Blood (without key).";
    }
}
