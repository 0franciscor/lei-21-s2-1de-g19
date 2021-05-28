package app.domain.model;

import com.example2.EMRefValue;
import com.example2.ExternalModule2API;

public class ExternalModuleBloodWithoutKey extends ExternalModule {

    ExternalModule2API em2;
    EMRefValue EMRefValue;
    ReferenceValue refValue;

    public ExternalModuleBloodWithoutKey(){
        em2 = new ExternalModule2API();
    }

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        EMRefValue = em2.getReferenceFor(parameter.getCode());
        refValue.setMinValue(EMRefValue.getMinValue());
        refValue.setMaxValue(EMRefValue.getMaxValue());
        return refValue;
    }

    @Override
    public String toString(){
        return String.format("External Module Blood (without key).");
    }
}
