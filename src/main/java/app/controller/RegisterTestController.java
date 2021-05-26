package app.controller;

import app.domain.model.*;
import auth.domain.store.ClientStore;
import auth.domain.store.ParameterStore;
import auth.domain.store.TestStore;
import auth.domain.store.TestTypeStore;
import auth.mappers.ClientMapper;
import auth.mappers.ParametersMapper;
import auth.mappers.TestTypeMapper;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.ParametersDto;
import auth.mappers.dto.TestTypeDto;
import java.util.List;

/**
 * Represents the controller that serves at intermediary between the UI and the domain layer.
 *
 * @author Eduardo Gonçalves
 */
public class RegisterTestController {


    /**
     * The controller's App object.
     */
    private App app;

    /**
     *  The controller's Company.
     */
    private Company company;

    /**
     * The controller's Client store.
     */
    public ClientStore clientstore;

    /**
     * The controller's Test store.
     */
    public TestStore testStore;


    /**
     * Builds a RegisterTestController without receiving parameters.
     */
    public RegisterTestController () {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.clientstore = company.getClientStore();
        this.testStore = company.getTestStore();

    }

    /**
     * Returns a client Dto that receives the citizen card number as a parameter.
     *
     * @param citizenID citizen card number
     * @return client Dto that receives the citizen card number as a parameter.
     */
    public ClientDto getClient (String citizenID) {

        ClientStore store = company.getClientStore();
        Client cl = store.getClient(citizenID);
        ClientDto cl1 = ClientMapper.toDto(cl);


        return cl1;

    }

    /**
     * Creates a Dto which is able to return a copy of all test types.
     *
     * @return a Dto containing a copy of all test types.
     */
    public List<TestTypeDto> getAllTestType (){

        TestTypeStore store = company.getTestTypeStore();
        List<TestType> listTestType = store.getAllTestTypes();
        List<TestTypeDto> listTestTypeDto = TestTypeMapper.toDto(listTestType);

        return listTestTypeDto;
    }

    /**
     * Creates a Dto which is able to return a copy of all parameters associated to the test type through the parameter category.
     *
     * @return a Dto which is able to return a copy of all parameters associated to the test type through the parameter category.
     */
    public List<ParametersDto> getAllParametersByTestType (TestTypeDto testTypeDto){

        ParameterStore store = company.getParameterStore();
        List<Parameter> listParameters = store.getAllParametersByTestType(testTypeDto);
        List<ParametersDto> listParametersDto = ParametersMapper.toDto(listParameters);

        return listParametersDto;
    }

    /**
     * Create a test that receives a list of parameters Dto, a test type Dto and a citizen card number as parameters.
     *
     * @param parameters the parameters Dto list
     * @param testTypeDto the test type Dto
     * @param citizenID the citizen card number
     * @return test.
     */
    public Test createTest (List<ParametersDto> parameters, TestTypeDto testTypeDto, String citizenID, String nhsCode){

        TestType testType = TestTypeMapper.toModel(testTypeDto);
        List<Parameter> parameters1 = ParametersMapper.toModel(parameters);
        TestStore store = company.getTestStore();
        Test test = testStore.createTest(testType, parameters1, citizenID, nhsCode);

        return test;
    }

    /**
     * If the saveTest method of the Test store is true then a code is generated and returns true, otherwise returns false.
     *
     * @return true if the saveTest method of the test store is true, otherwise return false.
     */
    public boolean saveTest (Test test) {

        if (this.testStore.saveTest(test)){
            test.generateCode();
            return true;
        }

        return false;
    }
}
