package app.domain.model;

import com.example2.EMRefValue;

/**
 * The ReferenceValue class, which is responsible for storing a certain Parameter's ID, minValue, maxValue and metric (unit).
 *
 * @author Francisco Redol
 */
public class ReferenceValue {

    /**
     * The Parameter's ID
     */
    private String parameterID;

    /**
     * The minimum value.
     */
    private double minValue;

    /**
     * The maximum value.
     */
    private double maxValue;

    /**
     * The metric unit.
     */
    private String metric;

    /**
     * @param parameterID
     * @param minValue
     * @param maxValue
     * @param metric
     *
     * Responsible for receiving all 4 params and creating a ReferenceValue object.
     */
    public ReferenceValue(String parameterID, double minValue, Double maxValue, String metric){
        this.parameterID = parameterID;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.metric = metric;
    }

    /**
     * @param emRefValue
     *
     * Responsible for receiving an EMRefValue object and convert it to a ReferenceValue object.
     */
    public ReferenceValue(EMRefValue emRefValue){
        this.parameterID = emRefValue.getParameterID();
        this.minValue = emRefValue.getMinValue();
        this.maxValue = emRefValue.getMaxValue();
        this.metric = emRefValue.getMetric();
    }

    /**
     * @return the parameter's ID.
     */
    public String getParameterID(){
        return this.parameterID;
    }

    /**
     * @return the minimum value.
     */
    public Double getMinValue(){
        return minValue;
    }

    /**
     * @return the maximum value.
     */
    public Double getMaxValue() {
        return maxValue;
    }

    /**
     * @return the metric (unit).
     */
    public String getMetric() {
        return metric;
    }

}
