package app.domain.model;

import com.example2.EMRefValue;

public class ReferenceValue {

    private String parameterID;

    private double minValue;

    private double maxValue;

    private String metric;


    public ReferenceValue(String parameterID, double minValue, Double maxValue, String metric){
        this.parameterID = parameterID;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.metric = metric;
    }

    public ReferenceValue(EMRefValue emRefValue){
        this.parameterID = emRefValue.getParameterID();
        this.minValue = emRefValue.getMinValue();
        this.maxValue = emRefValue.getMaxValue();
        this.metric = emRefValue.getMetric();
    }

    public Double getMinValue(){
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public String getMetric() {
        return metric;
    }

}
