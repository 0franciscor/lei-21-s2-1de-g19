package app.controller;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class RegisterTestControllerTest {

    @Test
    public void getClient (){


        /*

        RegisterTestController rtc = new RegisterTestController();


        Client client = new Client("1234567890987654","1212121212","12/12/2000","male","1212121212","12121212121","roberto@gmail.com","Roberto");
        rtc.clientstore.addClient(client);

        ClientDto clientDtoResult = rtc.getClient("1234567890987654");
        ClientDto clientDtoExpected = rtc.getClient(client.getCitizenID());

        Assert.assertEquals(clientDtoResult,clientDtoExpected);

         */

    }

    @Test
    public void getAllTestType() {


        /*
        RegisterTestController rtc = new RegisterTestController();
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();

        TestTypeDto testTypeDto = new TestTypeDto("45678", "cvfgt", "tyghy", new ParameterCategory("cvfrt","78906"));

        ttControllerTest.createTestType("45678", "cvfgt", "tyghy", new ParameterCategory("cvfrt", "78906"));
        ttControllerTest.saveTestType();

        List<TestTypeDto> testTypeDtoResult = rtc.getAllTestType();

        List<TestTypeDto> testTypeDtoExpected = new ArrayList<>();

        testTypeDtoExpected.add(testTypeDto);

        Assert.assertEquals(testTypeDtoResult,testTypeDtoExpected);

         */

    }

    @Test
    public void getAllParametersByTestType (){


    }

    @Test
    public void createTest (){

        /*
        RegisterTestController rtc = new RegisterTestController();

        Client client = new Client("1234567890987654","1212121212","12/12/2000","male","1212121212","12121212121","roberto@gmail.com","Roberto");
        rtc.clientstore.addClient(client);

        TestTypeDto testTypeDto = new TestTypeDto("34567","cvfgb","tyghu",new ParameterCategory("fgtry","34521"));

        TestType testType = new TestType("34567","cvfgb","tyghu",new ParameterCategory("fgtry","34521"));

        ParametersDto parametersDto = new ParametersDto("dfrtg","derfc","12345",new ParameterCategory("fgtvc","7yuj8"));
        List<ParametersDto> parametersDtoList = new ArrayList<>();
        parametersDtoList.add(parametersDto);

        Parameter parameter = new Parameter("dfrtg","derfc","12345",new ParameterCategory("fgtvc","7yuj8"));
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(parameter);

        app.domain.model.Test testResult = rtc.createTest(parametersDtoList, testTypeDto, "1234567890987654");

        app.domain.model.Test testExpected = new app.domain.model.Test(testType, parameterList, "1234567890987654" );

        Assert.assertEquals(testResult,testExpected);

         */

    }

    @Test
    public void saveTestTrue() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType testType = new TestType("34567","assdf","swab", pcList);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654","123456789098");

        RegisterTestController rtc = new RegisterTestController();

        boolean result = true;
        boolean expected = rtc.saveTest(test);

        Assert.assertEquals(result,expected);

    }

    @Test
    public void saveTestFalse() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType testType = new TestType("34567","assdf","swab", pcList);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654","123456789098");

        RegisterTestController rtc = new RegisterTestController();


        boolean result = false;
        rtc.testStore.addTest(test);
        boolean expected = rtc.saveTest(test);

        Assert.assertEquals(result,expected);
    }
}