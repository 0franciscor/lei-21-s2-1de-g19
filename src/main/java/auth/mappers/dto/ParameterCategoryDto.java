package auth.mappers.dto;

/**
 * ParameterCategoryDto class, which is responsible for converting a ParameterCategory List in a ParameterCategoryDto List.
 *
 * @author Francisco Redol (1201239)
 */
public class ParameterCategoryDto {

    /**
     * The ParameterCategoryDto name
     */
    private String name;

    /**
     * The ParameterCategoryDto code
     */
    private String code;


    /**
     * Builds the ParameterCategory object.
     *
     * @param name the ParameterCategoryDto name
     * @param code the ParameterCategoryDto code
     */
    public ParameterCategoryDto(String name, String code){
        this.name = name;
        this.code = code;
    }

    /**
     * Returns the ParameterCategoryDto name.
     *
     * @return ParameterCategoryDto name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the ParameterCategoryDto code.
     *
     * @return ParameterCategoryDto code
     */
    public String getCode(){
        return this.code;
    }

    /**
     * Returns a textual representation of the object, which contains all of its attributes.
     *
     * @return ParameterCategoryDto characteristics
     */

    @Override
    public String toString(){
        return String.format("The parameter category name is %s and it's code is %s.", this.name, this.code);
    }
}