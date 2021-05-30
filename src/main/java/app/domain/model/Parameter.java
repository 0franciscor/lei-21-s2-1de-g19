package app.domain.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents a Parameter.
 *
 * @author Rita Lello
 */
public class Parameter {

    /**
     * parameter's code.
     */
    private String code;

    /**
     * parameter's description.
     */
    private String description;

    /**
     * parameter's name.
     */
    private String designation;

    /**
     * parameter's category.
     */
    private ParameterCategory pcat;

    /**
     * parameter's code length.
     */
    private static final int CODE_LENGTH=5;

    /**
     * maximum length of parameter designation.
     */
    private static final int DESIGNATION_MAX_LENGTH=8;

    /**
     * maximum length of parameter description.
     */
    private static final int DESCRIPTION_MAX_LENGTH=20;

    /**
     * Builds a parameter instance that receives as parameters code, description, designation and pcat.
     * @param code
     * @param description
     * @param designation
     * @param pcat
     */
    public Parameter(String code, String description, String designation, ParameterCategory pcat){
        checkCodeRules(code);
        checkDescriptionRules(description);
        checkDesignationRules(designation);
        checkPcatRules(pcat);
        this.code=code;
        this.description=description;
        this.designation=designation;
        this.pcat=pcat;
    }

    /**
     * Builds an empty parameter instance.
     */
    public Parameter(){
    }

    /**
     * Returns the parameter code.
     * @return parameter code.
     */
    public String getCode(){
        return this.code;
    }

    /**
     * Returns the parameter description.
     * @return parameter description.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the parameter designation.
     * @return parameter designation.
     */
    public String getDesignation(){
        return this.designation;
    }

    /**
     * Returns the parameter category.
     * @return parameter category.
     */
    public ParameterCategory getPcat(){
        return this.pcat;
    }

    /**
     * Method responsible for checking the acceptance criteria for the parameter's code.
     * @param code
     */
    public void checkCodeRules(String code){
        if(StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if(code.length()!= CODE_LENGTH)
            throw new IllegalArgumentException("Code must have 5 chars.");
    }

    /**
     * Method responsible for checking the acceptance criteria for the parameter's description.
     * @param description
     */
    public void checkDescriptionRules(String description){
        if(StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if(description.length()>DESCRIPTION_MAX_LENGTH)
            throw new IllegalArgumentException("Description length is bigger than allowed.");
    }

    /**
     * Method responsible for checking the acceptance criteria for the parameter's designation.
     * @param designation
     */
    public void checkDesignationRules(String designation){
        if(StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        if(designation.length()>DESIGNATION_MAX_LENGTH)
            throw new IllegalArgumentException("Designation length is bigger than allowed.");
    }

    /**
     * Method responsible for checking the acceptance criteria for the parameter's category.
     * @param pcat
     */
    public void checkPcatRules(ParameterCategory pcat){
        if(pcat ==null)
            throw new NullPointerException("The parameter category is null.");
    }


    /**
     * Returns the textual description of the parameter.
     *
     * @return parameter characteristics.
     */

    public String toString(){
        return String.format("Parameter with code %s, description %s, and designation %s. %s", this.code, this.description, this.designation, this.pcat);
    }


    /**
     * Verifies if there is an object equals to the @param object.
     *
     * @param obj
     *
     * @return true if it founds an object equals to the @param.
     * @return false if there is not any object equals to the @param.
     */
    //@Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;

        if(this == obj)
            return true;

        Parameter obj2 = (Parameter) obj;

        return (this.code.equals(obj2.code) && this.description.equals(obj2.description) && this.designation.equals(obj2.designation) && this.pcat.equals(obj2.pcat));
    }
}
