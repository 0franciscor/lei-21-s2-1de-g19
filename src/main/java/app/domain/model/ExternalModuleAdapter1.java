package app.domain.model;

import com.example3.CovidReferenceValues1API;

public class ExternalModuleAdapter1 implements ExternalModule{

    CovidReferenceValues1API em1;
    ReferenceValue refValue;

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        refValue.setMinValue(em1.getMinReferenceValue(parameter.getCode(), 12345));
        refValue.setMaxValue(em1.getMaxReferenceValue(parameter.getCode(), 12345));
        return refValue;
    }
}
