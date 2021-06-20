package auth.domain.store;

import app.domain.model.ParameterCategory;
import app.domain.model.Test;
import auth.mappers.dto.TestTypeDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ParameterCategoryStore class, which is responsible for creating the ParameterCategoryStore object.
 *
 * @author Francisco Redol (1201239)
 */
public class ParameterCategoryStore implements Serializable {

    /**
     * List which keeps all the ParameterCategory objects created in the store.
     */
    private List<ParameterCategory> parameterCategoryList;

    /**
     * Builds the ParameterCategory object.
     *
     */
    public ParameterCategoryStore(){
        this.parameterCategoryList = new ArrayList<>();
    }

    /**
     * @param name
     * @param code
     *
     * Invokes the ParameterCategory Builder.
     *
     * @return created ParameterCategory, back to the controller
     */
    public ParameterCategory createParameterCategory(String name, String code){
        return new ParameterCategory(name, code);
    }

    /**
     * @param pc
     *
     * Adds a certain ParameterCategory after validating if it exists in the store list.
     *
     * @return a boolean stating if the ParameterCategory gotten by parameter was successfully added
     */
    public boolean addParameterCategory(ParameterCategory pc) {
        if (!validateParameterCategory(pc))
            return false;
        this.parameterCategoryList.add(pc);
        guardarFicheiroBinario(this);
        return true;
    }

    /**
     * @param pc
     *
     * Validates if a certain ParameterCategory already exists in the store list.
     *
     * @return a boolean stating if the ParameterCategory gotten by parameter already exists in the list.
     */
    public boolean validateParameterCategory(ParameterCategory pc){
        if(pc == null)
            return false;
        return !this.parameterCategoryList.contains(pc);
    }

    /**
     * @param code
     *
     * Searches for a specific ParameterCategory, through a parameter-sent code.
     *
     * @return the found ParameterCategoryObject or a null object, stating that it was not found.
     */
    public ParameterCategory getParameterCategoryByCode(String code){
        for(ParameterCategory pc : parameterCategoryList)
            if(pc.getCode().equalsIgnoreCase(code))
                return pc;
        return null;
    }

    /**
     * @param name
     *
     * Searches for a specific ParameterCategory, through a parameter-sent name.
     *
     * @return the found ParameterCategoryObject or a null object, stating that it was not found.
     */
    public ParameterCategory getParameterCategoryByName(String name){
        for(ParameterCategory pc : parameterCategoryList)
            if(pc.getName().equalsIgnoreCase(name))
                return pc;
        return null;
    }

    /**
     *
     * @return all ParameterCategory that exist in the store list
     */
    public List<ParameterCategory> getAllParameterCategories(){
        return parameterCategoryList;
    }

    /**
     * If the parameter category of the testTypeDto passed as a parameter is equal to a parameter category present in the parameterCategoryList,
     * it is added to a list and at the end the list is returned.
     *
     * @param testTypeDto a testTypeDto
     * @return a list that contains the testTypeDto parameter categories that are present in the parameterCategoryList
     */
    public List<ParameterCategory> getAllParameterCategoriesByTestType (TestTypeDto testTypeDto){

        List<ParameterCategory> parameterCategoryListByTestType = new ArrayList<>();

        for (ParameterCategory pc: parameterCategoryList ) {
            for(ParameterCategory pc2 : testTypeDto.getParameterCategoriesList())
                if (pc2.equals(pc)){
                    parameterCategoryListByTestType.add(pc);
                }
        }
        return parameterCategoryListByTestType;
    }
    public boolean guardarFicheiroBinario(ParameterCategoryStore store) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("parameterCategoryStore.bin"));
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
