package app.domain.model;

import com.example2.EMRefValue;
import com.example2.ExternalModule2API;

public class ExternalModuleAdapter2 implements ExternalModule{

    ExternalModule2API em2;
    EMRefValue EMRefValue;
    ReferenceValue refValue;
    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        EMRefValue = em2.getReferenceFor(parameter.getCode());
        refValue.setMinValue(EMRefValue.getMinValue());
        refValue.setMaxValue(EMRefValue.getMaxValue());
        return refValue;
    }
}
