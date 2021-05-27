package app.domain.model;

public class ReferenceValue {

    private Double minValue;
    private Double maxValue;

    public ReferenceValue(Double minValue, Double maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public void setMinValue(Double minValue){
        this.minValue = minValue;
    }

    public void setMaxValue(Double maxValue){
        this.maxValue = maxValue;
    }

    public Double getMinValue(){
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

}
