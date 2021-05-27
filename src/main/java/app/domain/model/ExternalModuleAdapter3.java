package app.domain.model;

import com.example1.ExternalModule3API;

public class ExternalModuleAdapter3 implements ExternalModule {

    ExternalModule3API em3;
    ReferenceValue refValue;


    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        refValue.setMinValue(em3.getMinReferenceValue(parameter.getCode(), 12345));
        refValue.setMaxValue(em3.getMaxReferenceValue(parameter.getCode(), 12345));
        return refValue;
    }
}
