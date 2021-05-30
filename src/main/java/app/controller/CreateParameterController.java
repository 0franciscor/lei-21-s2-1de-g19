package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import auth.domain.store.ParameterCategoryStore;
import auth.domain.store.ParameterStore;
import auth.mappers.ParameterCategoryMapper;
import auth.mappers.dto.ParameterCategoryDto;

import java.util.List;


/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Rita Lello
 */
public class CreateParameterController {

    /**
     * The controller's App object.
     */
    private App app;

    /**
     * The controller's Company.
     */
    private Company company;

    /**
     * The controller's Parameter Store.
     */
    private ParameterStore parameterStore;

    /**
     * The controller's Parameter Category Store.
     */
    private ParameterCategoryStore pcStore;

    /**
     * The controller's Parameter.
     */
    private Parameter parameter;

    /**
     * The controller's List of Parameter Categories.
     */
    private List<ParameterCategory> listCategories;

    /**
     * The controller's List of Parameter Categories in data transfer objects.
     */
    private List<ParameterCategoryDto> categoriesNamesDto;


    /**
     * Builds a CreateParameterController without receiving parameters.
     */
    public CreateParameterController(){
        this.app= app.getInstance();
        this.company=app.getCompany();
        this.parameterStore=company.getParameterStore();
        this.pcStore=company.getParameterCategoryStore();

    }

    /**
     * Calls the method from parameter category store that gets all the parameter categories and then calls the mapper method that converts the categories in data transfer objects.
     *
     * @return a list with the data transfer objects.
     */
    public List<ParameterCategoryDto> toDto(){
        listCategories=pcStore.getAllParameterCategories();
        categoriesNamesDto= ParameterCategoryMapper.toDto(listCategories);
        return categoriesNamesDto;
    }

    /**
     * Calls the method from parameter category store that gets the parameter category by a code and then call the parameter store method that creates a parameter with the category code caught before.
     *
     * @param code
     * @param description
     * @param designation
     * @param catCode
     * @return true if the parameter was successfully created.
     * @return false if the parameter was not created.
     */
    public void createParameter(String code, String description, String designation, ParameterCategoryDto catCode){
        ParameterCategory pcat = pcStore.getParameterCategoryByCode(catCode.getCode());
        this.parameter=parameterStore.create(code, description, designation, pcat);
    }

    /**
     * Calls the method form the parameter store and tries to save the controller's parameter.
     *
     * @return true if the parameter was successfully save into the parameters list.
     * @return false if the parameter was not save into the parameters list.
     */
    public boolean saveParameter(){
        return this.parameterStore.saveParameter(parameter);
    }

    /**
     * Calls the parameter store method that shows all the parameters.
     *
     * @return a list of all the parameters.
     */
    /*public List<Parameter> getAllParameters(){
        return parameterStore.getAllParameters();
    }*/


}
