package app.domain.model;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * TestType class, which is responsible for creating the TestType object.
 *
 * @author Francisco Redol (1201239)
 */
public class TestType {

    /**
     * The TestType code.
     */
    private String code;

    /**
     * The TestType description.
     */
    private String description;

    /**
     * The TestType collecting method.
     */
    private String collectingMethod;

    /**
     * The Parameter Categories List (since a TestType can have more than 1 ParameterCategory).
     */
    private List<ParameterCategory> parameterCategoriesList;

    /**
     * The exact code length, specified by the client.
     */
    private static final int CODE_LENGTH = 5;

    /**
     * The maximum description length, specified by the client.
     */
    private static final int DESCRIPTION_MAX_LENGTH = 15;
    /**
     * The maximum collection method length, specified by the client.
     */
    private static final int COLLECTING_METHOD_MAX_LENGTH = 20;

    /**
     * Builds the TestType object.
     *
     * @param code the Test Type code
     * @param description the Test Type description
     * @param collectingMethod the Test Type collecting method
     * @param parameterCategoriesList the list of Test Type's parameter categories
     */
    public TestType(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList){
        setCode(code);
        setDescription(description);
        setCollectingMethod(collectingMethod);
        setParameterCategoriesList(parameterCategoriesList);
    }

    /**
     * Returns the TestType code.
     *
     * @return TestType code
     */
    public String getCode(){
        return this.code;
    }

    /**
     * Returns the TestType description.
     *
     * @return TestType code
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the TestType collecting method.
     *
     * @return TestType collectingMethod
     */
    public String getCollectingMethod(){
        return this.collectingMethod;
    }

    /**
     * Returns the TestType list of parameter categories.
     *
     * @return TestType parameterCategoriesList
     */
    public List<ParameterCategory> getParameterCategoriesList(){
        return parameterCategoriesList;
    }


    /**
     * Modifies and checks the inserted parameter.
     *
     * @param code that is passed as parameter on the TestType Builder.
     */
    public void setCode(String code){
        if(StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be empty.");
        if(code.length() != CODE_LENGTH)
            throw new IllegalArgumentException("The code length is not correct.");

        this.code = code;
    }

    /**
     * Modifies and checks the inserted description.
     *
     * @param description that is passed as parameter on the TestType Builder.
     */
    public void setDescription(String description){
        if(StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be empty.");
        if(description.length() > DESCRIPTION_MAX_LENGTH)
            throw new IllegalArgumentException("The description length is bigger than allowed.");

        this.description = description;
    }

    /**
     * Modifies and checks the inserted collecting method.
     *
     * @param collectingMethod that is passed as parameter on the TestType Builder.
     */
    public void setCollectingMethod(String collectingMethod){
        if(StringUtils.isBlank(collectingMethod))
            throw new IllegalArgumentException("Collecting method cannot be empty.");
        if(collectingMethod.length() > COLLECTING_METHOD_MAX_LENGTH)
            throw new IllegalArgumentException("The collecting method length is bigger than allowed.");

        this.collectingMethod = collectingMethod;
    }

    /**
     * Modifies and checks the inserted parameter categories list.
     *
     * @param parameterCategoriesList that is passed as parameter on the TestType Builder.
     */
    public void setParameterCategoriesList(List<ParameterCategory> parameterCategoriesList){
        if(parameterCategoriesList.isEmpty())
            throw new NullPointerException("The parameter categories list is null.");

        this.parameterCategoriesList = parameterCategoriesList;
    }

    /**
     * Returns a textual representation of the object, which contains all of its attributes.
     *
     * @return TestType characteristics
     */
    @Override
    public String toString(){
        return String.format("Test type with code %s, analyses %s, and it's collecting method is %s. %s", this.code, this.description, this.collectingMethod, this.parameterCategoriesList);
    }

    /**
     * @param obj
     * Verifies if a certain object is equal to other.
     *
     * @return boolean result stating if the compared objects are equal
     */
//    @Override
//    public boolean equals(Object obj){
//        if(this == obj)
//            return true;
//
//        if(obj == null)
//            return false;
//
//        TestType obj2 = (TestType) obj;
//
//        return this.code.equals(obj2.code) && this.description.equals(obj2.description) && this.collectingMethod.equals(obj2.collectingMethod)
//                && this.parameterCategoriesList.equals(obj2.parameterCategoriesList);
//    }
}