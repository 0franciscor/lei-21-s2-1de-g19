package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import auth.domain.store.ParameterCategoryStore;
import auth.mappers.ParameterCategoryMapper;
import auth.mappers.dto.ParameterCategoryDto;
import java.util.List;

/**
 * CreateTestTypeController class, which is responsible for creating the TestTypeController object.
 *
 * @author Francisco Redol (1201239)
 */
public class CreateParameterCategoryController {

    /**
     * The ParameterCategoryController App.
     */
    private App app;

    /**
     * The ParameterCategoryController Company.
     */
    private Company company;

    /**
     * The ParameterCategoryController ParameterCategoryStore.
     */
    private ParameterCategoryStore pcStore;

    /**
     * The ParameterCategoryController ParameterCategory Object.
     */
    private ParameterCategory pc;

    /**
     * Builds the ParameterCategoryController, instantiating the app, the company and the ParameterCategoryStore
     *
     */
    public CreateParameterCategoryController(){
        this.app = app.getInstance();
        this.company = app.getCompany();
        pcStore = company.getParameterCategoryStore();
    }

    /**
     *
     * Creates a DTO which is able to return a copy of all Parameter Categories
     *
     * @return a DTO containing a copy of all Parameter Categories
     *
     */
    public List<ParameterCategoryDto> getAllParameterCategoriesDto(){
        List <ParameterCategory> parameterCategoriesList = pcStore.getAllParameterCategories();
        List <ParameterCategoryDto> pcListDto = ParameterCategoryMapper.toDto(parameterCategoriesList);
        return pcListDto;

        //return ParameterCategoryMapper.toDto(pcStore.getAllParameterCategories());
    }

    /**
     * @param name
     * @param code
     *
     * Invokes the creator method in the ParameterCategoryStore, and saves the created ParameterCategory as a class attribute.
     *
     * @return a boolean stating the process success
     */
    public boolean createParameterCategory(String name, String code){
        this.pc = pcStore.createParameterCategory(name, code);
        return true;
    }

    /**
     * Invokes the save method in the ParameterCategoryStore, which is then saved in the ParameterCategoryStore list.
     *
     * @return a boolean stating the process success
     */
    public boolean saveParameterCategory(){
        return pcStore.addParameterCategory(pc);
    }

    /**
     * @param code
     *
     * Receives a code as a parameter, which is then sent to the ParameterCategoryStore.
     * @return a ParameterCategory which is sent by the ParameterCategoryStore
     */
    public ParameterCategory getParameterCategoryByCode(String code){
        return pcStore.getParameterCategoryByCode(code);
    }

    /**
     * Invokes the method in the ParameterCategoryStore
     *
     * @return a list that contains all the Parameter Categories existent in the ParameterCategoryStore
     */
    public List<ParameterCategory> getAllParameterCategories(){
        return pcStore.getAllParameterCategories();
    }
}