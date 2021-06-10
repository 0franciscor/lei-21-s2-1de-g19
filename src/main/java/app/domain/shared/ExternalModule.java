package app.domain.shared;

import app.domain.model.ReferenceValue;
import app.domain.model.TestParameter;

/**
 * The External Module abstract class.
 *
 * @author Francisco Redol
 */
public abstract class ExternalModule {

    /**
     * @param testParameter the TestParameter
     * @return a reference value.
     */
    public abstract ReferenceValue getReferenceValue(TestParameter testParameter);

    /**
     * @return a String containing External Module information.
     */
    @Override
    public String toString(){
        return "External module";
    }

}
