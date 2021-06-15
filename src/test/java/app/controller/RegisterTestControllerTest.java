package app.controller;

import app.domain.model.*;
import app.domain.shared.ExternalModuleBloodWithoutKey;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.ParameterCategoryDto;
import auth.mappers.dto.ParametersDto;
import auth.mappers.dto.TestTypeDto;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class RegisterTestControllerTest {

    @Test
    public void getClient (){

        RegisterTestController rtc = new RegisterTestController();

        Client client = new Client("1234567890987654","1212121212","12/12/2000","male","1212121212","12121212121","roberto@gmail.com","Roberto", "Rua das Palmeiras");
        rtc.clientstore.addClient(client);

        ClientDto clientDtoResult = rtc.getClient("1212121212");

        ClientDto clientDtoExpected = new ClientDto("1234567890987654","1212121212","12/12/2000","male","1212121212","12121212121","roberto@gmail.com","Roberto", "Rua das Palmeiras");

        Assert.assertEquals(clientDtoResult.getCitizenID(),clientDtoExpected.getCitizenID());
        Assert.assertEquals(clientDtoResult.getNhsID(),clientDtoExpected.getNhsID());
        Assert.assertEquals(clientDtoResult.getBirthDate(),clientDtoExpected.getBirthDate());
        Assert.assertEquals(clientDtoResult.getSex(),clientDtoExpected.getSex());
        Assert.assertEquals(clientDtoResult.getTIN(),clientDtoExpected.getTIN());
        Assert.assertEquals(clientDtoResult.getPhoneNumber(),clientDtoExpected.getPhoneNumber());
        Assert.assertEquals(clientDtoResult.getEmail(),clientDtoExpected.getEmail());
        Assert.assertEquals(clientDtoResult.getName(),clientDtoExpected.getName());

    }

    @Test
    public void getAllTestType() {

//        RegisterTestController rtc = new RegisterTestController();
//        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
//
//        List<ParameterCategory> listParameterCategories = new ArrayList<>();
//        ParameterCategory parameterCategory = new ParameterCategory("cvfrt","78906");
//        listParameterCategories.add(parameterCategory);
//
//        TestTypeDto testTypeDto = new TestTypeDto("45678", "cvfgt", "tyghy", listParameterCategories, new ExternalModuleBloodWithoutKey());
//
//        ttControllerTest.createTestType("45678", "cvfgt", "tyghy", listParameterCategories, new ExternalModuleBloodWithoutKey());
//        ttControllerTest.saveTestType();
//
//        List<TestTypeDto> testTypeDtoResult = rtc.getAllTestType();
//
//        List<TestTypeDto> testTypeDtoExpected = new ArrayList<>();
//
//        testTypeDtoExpected.add(testTypeDto);
//
//        Assert.assertEquals(testTypeDtoResult.toString(),testTypeDtoExpected.toString());

    }

    @Test
    public void getAllParameterCategoriesByTestType (){

        RegisterTestController rtc = new RegisterTestController();
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        CreateParameterCategoryController parameterCategoryController = new CreateParameterCategoryController();

        List<ParameterCategory> listParameterCategories = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("cvfrt","78906");
        listParameterCategories.add(parameterCategory);

        TestTypeDto testTypeDto = new TestTypeDto("45678", "cvfgt", "tyghy", listParameterCategories, new ExternalModuleBloodWithoutKey());

        ttControllerTest.createTestType("45678", "cvfgt", "tyghy", listParameterCategories, new ExternalModuleBloodWithoutKey());
        ttControllerTest.saveTestType();

        parameterCategoryController.createParameterCategory("cvfrt","78906");
        parameterCategoryController.saveParameterCategory();

        List<ParameterCategoryDto> parameterCategoryDtoListResult = rtc.getAllParameterCategoriesByTestType(testTypeDto);

        ParameterCategoryDto parameterCategoryDto = new ParameterCategoryDto("cvfrt", "78906");

        List<ParameterCategoryDto> parameterCategoryDtoListExpected = new ArrayList<>();
        parameterCategoryDtoListExpected.add(parameterCategoryDto);

        Assert.assertEquals(parameterCategoryDtoListResult.toString(),parameterCategoryDtoListExpected.toString());
    }


    @Test
    public void getAllParametersByParameterCategory (){

        RegisterTestController rtc = new RegisterTestController();
        CreateParameterCategoryController parameterCategoryController = new CreateParameterCategoryController();
        CreateParameterController parameterController = new CreateParameterController();

        List<ParameterCategoryDto> parameterCategoryDtoList = new ArrayList<>();
        ParameterCategoryDto parameterCategoryDto = new ParameterCategoryDto("cvfrt","78906");
        parameterCategoryDtoList.add(parameterCategoryDto);

        parameterCategoryController.createParameterCategory("cvfrt","78906");
        parameterCategoryController.saveParameterCategory();

        parameterController.createParameter("12345","hjuio","bnhyu",parameterCategoryDto);
        parameterController.saveParameter();

        List<ParametersDto> parametersDtoListResult = rtc.getAllParametersByParameterCategory(parameterCategoryDtoList);

        ParameterCategory parameterCategory = new ParameterCategory("cvfrt","78906");

        ParametersDto parametersDto = new ParametersDto("bnhyu","hjuio","12345",parameterCategory);

        List<ParametersDto> parametersDtoListExpected = new ArrayList<>();
        parametersDtoListExpected.add(parametersDto);

        Assert.assertEquals(parametersDtoListResult.toString(),parametersDtoListExpected.toString());

    }

    @Test
    public void createTest (){

        RegisterTestController rtc = new RegisterTestController();

        Client client = new Client("1234567890987654","1212121212","12/12/2000","male","1212121212","12121212121","roberto@gmail.com","Roberto", "Rua das Palmeiras");
        rtc.clientstore.addClient(client);

        List<ParameterCategory> listParameterCategories = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("fgtry","34521");
        listParameterCategories.add(parameterCategory);

        TestTypeDto testTypeDto = new TestTypeDto("34567","cvfgb","tyghu", listParameterCategories, new ExternalModuleBloodWithoutKey());

        TestType testType = new TestType("34567","cvfgb","tyghu",listParameterCategories, new ExternalModuleBloodWithoutKey());

        ParametersDto parametersDto = new ParametersDto("dfrtg","derfc","12345",new ParameterCategory("fgtvc","7yuj8"));
        List<ParametersDto> parametersDtoList = new ArrayList<>();
        parametersDtoList.add(parametersDto);

        Parameter parameter = new Parameter("dfrtg","derfc","12345",new ParameterCategory("fgtvc","7yuj8"));
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(parameter);

        ParameterCategoryDto parameterCategoryDto = new ParameterCategoryDto("covid","c0vid");
        List<ParameterCategoryDto> parameterCategoryDtoList = new ArrayList<>();
        parameterCategoryDtoList.add(parameterCategoryDto);

        ParameterCategory parameterCategory1 = new ParameterCategory("covid","c0vid");
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        parameterCategoryList.add(parameterCategory1);

        app.domain.model.Test testResult = rtc.createTest(parametersDtoList, testTypeDto, parameterCategoryDtoList, "1234567890","121212121212");

        app.domain.model.Test testExpected = new app.domain.model.Test(testType, parameterList, parameterCategoryList, "1234567890","121212121212");

        Assert.assertEquals(testResult.toString(),testExpected.toString());

    }

    /*
    @Test
    public void saveTestTrue() {


        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType testType = new TestType("34567","assdf","swab", pcList, new ExternalModuleBloodWithoutKey());

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,pcList,"1234567890","123456789098");

        RegisterTestController rtc = new RegisterTestController();

        boolean result = true;
        boolean expected = rtc.saveTest(test);

        Assert.assertEquals(result,expected);

    }

     */

    /*
    @Test
    public void saveTestFalse() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType testType = new TestType("34567","assdf","swab", pcList, new ExternalModuleBloodWithoutKey());

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,pcList,"1234567890","123456789098");

        RegisterTestController rtc = new RegisterTestController();


        boolean result = false;
        rtc.testStore.addTest(test);
        boolean expected = rtc.saveTest(test);

        Assert.assertEquals(result,expected);

    }

     */
}