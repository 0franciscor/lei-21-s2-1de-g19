package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import auth.domain.store.TestTypeStore;
import auth.mappers.TestTypeMapper;
import auth.mappers.dto.TestTypeDto;

/**
 * CreateTestTypeController class, which is responsible for creating the TestTypeController object.
 *
 * @author Francisco Redol (1201239)
 */
public class CreateTestTypeController {

    /**
     * The TestTypeController App.
     */
    private App app;

    /**
     * The TestTypeController Company.
     */
    private Company company;

    /**
     * The TestTypeController ParameterCategoryStore.
     */
    private TestTypeStore ttStore;

    /**
     * The TestTypeController TestType Object.
     */
    private TestType tt;

    /**
     * Builds the TestTypeController, instantiating the app, the company and the TestTypeStore
     *
     */
    public CreateTestTypeController(){
        this.app = app.getInstance();
        this.company = app.getCompany();
        this.ttStore = company.getTestTypeStore();
    }

    /**
     *
     * Creates a DTO which is able to return a copy of all TestTypes.
     *
     * @return a DTO containing a copy of all TestTypes
     *
     */
    public List<TestTypeDto> getAllTestTypesDto(){
        return TestTypeMapper.toDto(ttStore.getAllTestTypes());
    }

    /**
     * @param code the Test Type code
     * @param description the Test Type description
     * @param collectingMethod the Test Type collecting method
     * @param parameterCategory the TestType ParameterCategory
     *
     * Invokes the creator method in the TestTypeStore, and saves the created TestType as a class attribute.
     *
     * @return a boolean stating the process success
     */
    public boolean createTestType(String code, String description, String collectingMethod, ParameterCategory parameterCategory){
        this.tt = ttStore.createTestType(code, description, collectingMethod, parameterCategory);
        return true;
    }

    /**
     * @param code the Test Type code
     * @param description the Test Type description
     * @param collectingMethod the Test Type collecting method
     * @param parameterCategoriesList the TestType ParameterCategory List
     *
     * Invokes the creator method in the TestTypeStore, and saves the created TestType as a class attribute.
     *
     * @return a boolean stating the process success
     */
    public boolean createTestType(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList){
        this.tt = ttStore.createTestType(code, description, collectingMethod, parameterCategoriesList);
        return true;
    }

    /**
     * Invokes the save method in the TestTypeStore, which is then saved in the TestTypeStore list.
     *
     * @return a boolean stating the process success
     */
    public boolean saveTestType(){
       return ttStore.saveTestType(tt);
    }

    /**
     * @param code
     *
     * Receives a code as a parameter, which is then sent to the TestTypeStore.
     * @return a TestType which is sent by the TestTypeStore
     */
    public TestType getTestTypeByCode(String code) {
        return ttStore.getTestTypeByCode(code);
    }

    /**
     * Invokes the method in the TestTypeStore
     *
     * @return a list that contains all the Test Types existent in the TestTypeStore
     */
    public List<TestType> getAllTestTypes(){
        return ttStore.getAllTestTypes();
    }
}