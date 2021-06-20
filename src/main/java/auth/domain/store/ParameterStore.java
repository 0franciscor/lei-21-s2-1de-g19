package auth.domain.store;

import app.controller.App;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.Test;
import auth.mappers.dto.ParameterCategoryDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the parameter store class.
 *
 * @author Rita Lello
 */
public class ParameterStore implements Serializable {

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
            guardarFicheiroBinario(this);
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
     * If the parameter category of the listParameterCategoryDto passed as a parameter is equal to the parameter category of one of all the existing parameters,
     * this parameter is added to a list and at the end the list is returned.
     *
     * @param listParameterCategoryDto a list of parameter categories Dto
     * @return a list that contains parameters, with specific parameter categories
     */
    public List<Parameter> getAllParametersByParameterCategory (List<ParameterCategoryDto> listParameterCategoryDto){

        List<Parameter> parameterStoreListByParameterCategory = new ArrayList<>();

        ParameterCategoryStore store = App.getInstance().getCompany().getParameterCategoryStore();


        for (ParameterCategoryDto pcdto : listParameterCategoryDto){
            for (Parameter p: parameterStoreList){
                if (p.getPcat().equals(store.getParameterCategoryByCode(pcdto.getCode()))){
                    parameterStoreListByParameterCategory.add(p);
                }
            }
        }

       return parameterStoreListByParameterCategory;
    }

    /**
     * @param code that identifies a certain Parameter
     *
     * Method that searches for a specific parameter on the store and returns it.
     *
     * @return the parameter correspondent to that code
     */
    public Parameter getParameterByCode(String code){
        for(Parameter parameter : parameterStoreList)
            if(parameter.getCode().equalsIgnoreCase(code))
                return parameter;

        return null;
    }
    public boolean guardarFicheiroBinario(ParameterStore store) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("parameterStore.bin"));
            try {
                out.writeObject(store);
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }
}
