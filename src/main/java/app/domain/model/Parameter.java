package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class Parameter {
    private String code;
    private String description;
    private String designation;
    private ParameterCategory pcat;

    private static final int CODE_LENGTH=5;
    private static final int DESIGNATION_LENGTH=8;
    private static final int DESCRIPTION_LENGTH=20;

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

    private void checkCodeRules(String code){
        if(StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if(code.length()!= CODE_LENGTH)
            throw new IllegalArgumentException("Code must have 5 chars.");
    }

    private void checkDescriptionRules(String description){
        if(StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if(description.length()>DESCRIPTION_LENGTH)
            throw new IllegalArgumentException("Description length is bigger than allowed.");
    }

    private void checkDesignationRules(String designation){
        if(StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        if(designation.length()>DESIGNATION_LENGTH)
            throw new IllegalArgumentException("Designation length is bigger than allowed.");
    }

    private void checkPcatRules(ParameterCategory pcat){
        if(pcat ==null)
            throw new NullPointerException("The parameter category is null.");
    }

    @Override
    public String toString(){
        return String.format("Parameter with code %s, description %s, and designation %s. It has %s as Parameter Category.", this.code, this.description, this.designation, this.pcat);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;

        if(this == obj)
            return true;

        Parameter obj2 = (Parameter) obj;

        return (this.code.equals(obj2.code) && this.description.equals(obj2.description) && this.designation.equals(obj2.designation) && this.pcat.equals(obj2.pcat));
    }
}
