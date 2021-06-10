package app.controller;

import app.domain.shared.ExternalModule;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import auth.domain.store.TestTypeStore;
import auth.mappers.TestTypeMapper;
import auth.mappers.dto.TestTypeDto;

import java.util.List;

/**
 * CreateTestTypeController class, which is responsible for creating the TestTypeController object.
 *
 * @author Francisco Redol (1201239)
 */
public class CreateTestTypeController {

    /**
     * The TestTypeController TestType Object.
     */
    private TestType tt;

    /**
     * Builds the TestTypeController, instantiating the app, the company and the TestTypeStore
     *
     */
    public CreateTestTypeController(){}

    /**
     *
     * Creates a DTO which is able to return a copy of all TestTypes.
     *
     * @return a DTO containing a copy of all TestTypes
     *
     */
    public List<TestTypeDto> getAllTestTypesDto(){
        TestTypeStore ttStore = App.getInstance().getCompany().getTestTypeStore();
        return TestTypeMapper.toDto(ttStore.getAllTestTypes());
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
    public boolean createTestType(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList, ExternalModule externalModule){
        TestTypeStore ttStore = App.getInstance().getCompany().getTestTypeStore();
        this.tt = ttStore.createTestType(code, description, collectingMethod, parameterCategoriesList, externalModule);
        return true;
    }

    /**
     * Invokes the save method in the TestTypeStore, which is then saved in the TestTypeStore list.
     *
     * @return a boolean stating the process success
     */
    public boolean saveTestType() {
        TestTypeStore ttStore = App.getInstance().getCompany().getTestTypeStore();
        return ttStore.saveTestType(tt);
    }

    /**
     * @param code
     *
     * Receives a code as a parameter, which is then sent to the TestTypeStore.
     * @return a TestType which is sent by the TestTypeStore
     */
    public TestType getTestTypeByCode(String code) {
        TestTypeStore ttStore = App.getInstance().getCompany().getTestTypeStore();
        return ttStore.getTestTypeByCode(code);
    }

    /**
     * Invokes the method in the TestTypeStore
     *
     * @return a list that contains all the Test Types existent in the TestTypeStore
     */
    public List<TestType> getAllTestTypes(){
        TestTypeStore ttStore = App.getInstance().getCompany().getTestTypeStore();
        return ttStore.getAllTestTypes();
    }

    /**
     * Invokes the method in Company to retrieve the available external Modules
     *
     * @return a list that contains all the existent external Modules.
     */
    public List<ExternalModule> getExternalModules(){
        List<ExternalModule> externalModulesList;
        externalModulesList = App.getInstance().getCompany().getExternalModules();
        return externalModulesList;

    }
}