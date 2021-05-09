package auth.domain.store;

import app.domain.model.ParameterCategory;
import java.util.ArrayList;
import java.util.List;

/**
 * ParameterCategoryStore class, which is responsible for creating the ParameterCategoryStore object.
 *
 * @author Francisco Redol (1201239)
 */
public class ParameterCategoryStore {

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
     * Saves the received ParameterCategory Object, after validating it (then adding it).
     *
     * @returna a boolean stating the success of saving the received ParameterCategory (true if successful, false if it can't save)
     */
    public boolean saveParameterCategory(ParameterCategory pc){
        return addParameterCategory(pc);
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
     * @param pc
     *
     * Adds a certain ParameterCategory after validating if it exists in the store list.
     *
     * @return a boolean stating if the ParameterCategory gotten by parameter was successfully added
     */
    public boolean addParameterCategory(ParameterCategory pc) {
        if (!validateParameterCategory(pc))
            return false;
        return this.parameterCategoryList.add(pc);
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
        return new ParameterCategory ("NOT_FOUND", "NOT_FOUND");
    }

    /**
     *
     * @return all ParameterCategory that exist in the store list
     */
    public List<ParameterCategory> getAllParameterCategories(){
        return parameterCategoryList;
    }
}
