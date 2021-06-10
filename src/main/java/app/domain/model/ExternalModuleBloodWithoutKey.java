package app.domain.model;

import com.example2.EMRefValue;
import com.example2.ExternalModule2API;

public class ExternalModuleBloodWithoutKey extends ExternalModule {

    ExternalModule2API externalModule2API;

    public ExternalModuleBloodWithoutKey(){
        externalModule2API = new ExternalModule2API();
    }

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        EMRefValue ReferenceValue = externalModule2API.getReferenceFor(parameter.getCode());
        return new ReferenceValue(ReferenceValue);
    }


    @Override
    public String toString(){
        return String.format("External Module Blood (without key).");
    }
}
