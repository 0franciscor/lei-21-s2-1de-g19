package auth.mappers.dto;

import app.domain.model.ParameterCategory;

public class ParametersDto {


    private String designation;
    private String description;
    private String code;
    private ParameterCategory parameterCategory;

    public ParametersDto (String designation, String description, String code, ParameterCategory parameterCategory){
        this.designation = designation;
        this.description = description;
        this.code = code;
        this.parameterCategory = parameterCategory;
    }

    public String getDesignation (){
        return designation;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public ParameterCategory getParameterCategory() {
        return parameterCategory;
    }

    @Override
    public String toString(){

        return String.format("The parameter name is %s with the description %s and  it's code is %s.", this.designation, this.description, this.code);
    }
}
