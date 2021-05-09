package auth.mappers.dto;

import app.domain.model.ParameterCategory;
import java.util.List;

/**
 * TestTypeDto class, which is responsible for creating TestTypeDto.
 *
 * @author Francisco Redol (1201239)
 */
public class TestTypeDto {

    /**
     * The TestTypeDto code.
     */
    private String code;

    /**
     * The TestTypeDto description.
     */
    private String description;

    /**
     * The TestTypeDto collecting method.
     */
    private String collectingMethod;

    /**
     * The TestTypeDto Parameter category (created by the ParameterCategory class).
     */
    private ParameterCategory parameterCategory;

    /**
     * The TestTypeDto Parameter Categories List (since a TestType can have more than 1 ParameterCategory).
     */
    private List<ParameterCategory> parameterCategoriesList;

    /**
     * Builds the TestType object.
     *
     * @param code the Test Type Dto code
     * @param description the Test Type Dto description
     * @param collectingMethod the Test Type Dto collecting method
     * @param parameterCategory the TestType Dto ParameterCategory
     */
    public TestTypeDto(String code, String description, String collectingMethod, ParameterCategory parameterCategory){
        this.code = code;
        this.description = description;
        this.collectingMethod = collectingMethod;
        this.parameterCategory = parameterCategory;
    }

    /**
     * Builds the TestType object.
     *
     * @param code the Test Type Dto code
     * @param description the Test Type Dto description
     * @param collectingMethod the Test Type Dto collecting method
     * @param parameterCategoriesList the list of Test Type's Dto parameter categories
     */
    public TestTypeDto(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList){
        this.code = code;
        this.description = description;
        this.collectingMethod = collectingMethod;
        this.parameterCategoriesList = parameterCategoriesList;
    }

    /**
     * Returns the TestTypeDto code.
     *
     * @return TestTypeDto code
     */
    public String getCode(){
        return this.code;
    }

    /**
     * Returns the TestTypeDto description.
     *
     * @return TestTypeDto code
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the TestTypeDto collecting method.
     *
     * @return TestTypeDto collectingMethod
     */
    public String getCollectingMethod(){
        return this.collectingMethod;
    }

    /**
     * Returns the TestTypeDto Parameter Category.
     *
     * @return TestTypeDto parameterCategory
     */
    public ParameterCategory getParameterCategory(){
        return this.parameterCategory;
    }

    /**
     * Returns the TestTypeDto list of parameter categories.
     *
     * @return TestTypeDto parameterCategoriesList
     */
    public List<ParameterCategory> getParameterCategoriesList(){
        return parameterCategoriesList;
    }

    /**
     * Returns a textual representation of the object, which contains all of its attributes.
     *
     * @return TestTypeDto characteristics
     */
    @Override
    public String toString(){
        if(this.parameterCategoriesList == null)
            return String.format("Test type with code %s, analyses %s, and it's collecting method is %s. %s", this.code, this.description, this.collectingMethod, this.parameterCategory);
        else
            return String.format("Test type with code %s, analyses %s, and it's collecting method is %s. %s", this.code, this.description, this.collectingMethod, this.parameterCategoriesList);
    }
}
