package app.domain.model;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class TestType {

    private String code;
    private String description;
    private String collectingMethod;
    private ParameterCategory parameterCategory;
    private List<ParameterCategory> parameterCategoriesList;

    private static final int CODE_LENGTH = 5;
    private static final int DESCRIPTION_MAX_LENGTH = 15;
    private static final int COLLECTING_METHOD_MAX_LENGTH = 20;


    public TestType(String code, String description, String collectingMethod, ParameterCategory parameterCategory){
        setCode(code);
        setDescription(description);
        setCollectingMethod(collectingMethod);
        setParameterCategory(parameterCategory);
    }

    public TestType(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList){
        setCode(code);
        setDescription(description);
        setCollectingMethod(collectingMethod);
        setParameterCategoriesList(parameterCategoriesList);
    }

    //GET'S

    public String getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }

    public String getCollectingMethod(){
        return this.collectingMethod;
    }

    public ParameterCategory getParameterCategory(){
        return this.parameterCategory;
    }

    public List<ParameterCategory> getParameterCategoriesList(){
        return this.parameterCategoriesList;
    }

    //SET'S com CHECK's

    public void setCode(String code){
        if(StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be empty.");
        if(code.length() != CODE_LENGTH)
            throw new IllegalArgumentException("The code length is not correct.");

        this.code = code;
    }

    public void setDescription(String description){
        if(StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be empty.");
        if(description.length() > DESCRIPTION_MAX_LENGTH)
            throw new IllegalArgumentException("The description length is bigger than allowed.");

        this.description = description;
    }

    public void setCollectingMethod(String collectingMethod){
        if(StringUtils.isBlank(collectingMethod))
            throw new IllegalArgumentException("Collecting method cannot be empty.");
        if(collectingMethod.length() > COLLECTING_METHOD_MAX_LENGTH)
            throw new IllegalArgumentException("The collecting method length is bigger than allowed.");

        this.collectingMethod = collectingMethod;
    }

    public void setParameterCategory(ParameterCategory parameterCategory){
        if(parameterCategory == null)
            throw new NullPointerException("The parameter category is null.");

        this.parameterCategory = parameterCategory;
    }

    public void setParameterCategoriesList(List<ParameterCategory> parameterCategoriesList){
        if(parameterCategoriesList.isEmpty())
            throw new NullPointerException("The parameter categories list is null.");

        this.parameterCategoriesList = parameterCategoriesList;
    }

    @Override
    public String toString(){
        if(this.parameterCategory != null)
            return String.format("Test type with code %s, analyses %s, and it's collecting method is %s. It has %s as Parameter Category.", this.code, this.description, this.collectingMethod, this.parameterCategory);
        else
            return String.format("Test type with code %s, analyses %s, and it's collecting method is %s. It has %s as Parameter Category List.", this.code, this.description, this.collectingMethod, this.parameterCategoriesList);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;

        if(this == obj)
            return true;

        TestType obj2 = (TestType) obj;

        return (this.code.equals(obj2.code) && this.description.equals(obj2.description) && this.collectingMethod.equals(obj2.collectingMethod)
                && this.parameterCategory.equals(obj2.parameterCategory) && this.parameterCategoriesList.equals(obj2.parameterCategoriesList));
    }
}
