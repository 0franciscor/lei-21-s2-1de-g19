package auth.domain.store;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import auth.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the parameter store class.
 *
 * @author Rita Lello
 */
public class ParameterStore {

    /**
     * The store's parameters list.
     */
    private List<Parameter> parameterStoreList;



    /**
     * Builds a Parameter Store without receiving parameters.
     */
    public ParameterStore(){
        this.parameterStoreList=new ArrayList<>();
    }

    /**
     * Creates a new parameter by calling the parameter class.
     *
     * @param code
     * @param description
     * @param designation
     * @param pcat
     * @return the created parameter.
     */
    public Parameter create(String code, String description, String designation, ParameterCategory pcat){
        Parameter parameter = new Parameter(code, description, designation, pcat);
        return parameter;
    }

    /**
     * Saves the parameter in the parameter store list.
     *
     * @param parameter
     * @return true if the parameter was successfully save in the parameter store list.
     * @return false if the parameter was not save in the parameter store list.
     */
    public boolean saveParameter(Parameter parameter){
        if(parameter!=null&&validate(parameter)){
            add(parameter);
            return true;
        }
        return false;
    }

    /**
     * Sees if the parameter is valid.
     *
     * @param parameter
     * @return true if the parameter is not already in the store.
     * @return false if the parameter is already in the store.
     */
    public boolean validate(Parameter parameter){
        if(parameterStoreList.contains(parameter))
            return false;
        return true;
    }

    /**
     * Shows all the parameters.
     *
     * @return a list of all the parameters.
     */
    /*public List<Parameter> getAllParameters(){
        return parameterStoreList;
    }*/

    /**
     * Add a parameter to the store list.
     * @param parameter
     */
    public void add(Parameter parameter){
        parameterStoreList.add(parameter);
    }


    /**
     * Returns the parameter list associated with test type through the parameter category.
     *
     * @param testTypeDto a testTypeDto
     * @return parameter list associated with test type through the parameter category
     */
    public List<Parameter> getAllParametersByTestType (TestTypeDto testTypeDto){

        List<Parameter> parameterStoreListTestType = new ArrayList<>();

        for (Parameter p: parameterStoreList ) {
            for(ParameterCategory pc : testTypeDto.getParameterCategoriesList())
                if (pc.equals(p.getPcat())){
                    parameterStoreListTestType.add(p);
                }
        }
        return parameterStoreListTestType;
    }
}
