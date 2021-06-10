package auth.mappers.dto;

import app.domain.shared.ExternalModule;
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
     * The TestTypeDto Parameter Categories List (since a TestType can have more than 1 ParameterCategory).
     */
    private List<ParameterCategory> parameterCategoriesList;

    /**
     * The TestTypeDto External module.
     */
    private ExternalModule externalModule;

    /**
     * Builds the TestType object.
     *
     * @param code the Test Type Dto code
     * @param description the Test Type Dto description
     * @param collectingMethod the Test Type Dto collecting method
     * @param parameterCategoriesList the list of Test Type's Dto parameter categories
     */
    public TestTypeDto(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList, ExternalModule externalModule){
        this.code = code;
        this.description = description;
        this.collectingMethod = collectingMethod;
        this.parameterCategoriesList = parameterCategoriesList;
        this.externalModule = externalModule;
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
     * Returns the TestTypeDto list of parameter categories.
     *
     * @return TestTypeDto parameterCategoriesList
     */
    public List<ParameterCategory> getParameterCategoriesList(){
        return parameterCategoriesList;
    }

    /**
     * Returns the TestTypeDto external module.
     *
     * @return the TestTypeDto external module.
     */
    public ExternalModule getExternalModule(){
        return externalModule;
    }

    /**
     * Returns a textual representation of the object, which contains all of its attributes.
     *
     * @return TestTypeDto characteristics
     */
    @Override
    public String toString(){
        return String.format("Test type with code %s, analyses %s, and it's collecting method is %s. %s %s", this.code, this.description, this.collectingMethod, this.parameterCategoriesList, this.externalModule);
    }

//    @Override
//    public boolean equals(Object obj){
//        if(this == obj)
//            return true;
//
//        if(obj == null)
//            return false;
//
//        TestTypeDto obj2 = (TestTypeDto) obj;
//
//        return this.code.equals(obj2.code) && this.description.equals(obj2.description) && this.collectingMethod.equals(obj2.collectingMethod)
//                && this.parameterCategoriesList.equals(obj2.parameterCategoriesList) && this.externalModule.equals(obj2.externalModule);
//    }
}
