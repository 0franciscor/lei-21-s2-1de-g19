package auth.domain.store;

import app.controller.App;
import app.domain.model.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestStoreTest {

    @Test
    public void createTest (){

        TestStore testStore = new TestStore();

        Client cl = new Client("1234567890987654","1234567890","12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto");

        ParameterCategory parameterCategory = new ParameterCategory("rfgvb","45678");
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(parameterCategory);

        TestType testType = new TestType("12345", "cvfrt", "derfc", pcList, new ExternalModuleBloodWithoutKey());

        List<Parameter> parameterList = new ArrayList<>();
        Parameter parameter = new Parameter("69870","fcvbn","yuhbc", parameterCategory);
        parameterList.add(parameter);

        app.domain.model.Test test = testStore.createTest(testType,parameterList, pcList, cl.getTIN(),"121212121212");

        Assert.assertEquals(test.toString(),testStore.createTest(testType, parameterList, pcList, cl.getTIN(),"121212121212").toString());
    }

    @Test
    public void saveTestInStore() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType testType = new TestType("34567","assdf","swab", pcList, new ExternalModuleBloodWithoutKey());

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,pcList,"1234567890987654","123456789098");

        TestStore testStore = new TestStore();

        boolean result = true;
        boolean expected = testStore.saveTest(test);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void DoesNotsaveTestInStore() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType testType = new TestType("34567","assdf","swab", pcList, new ExternalModuleBloodWithoutKey());

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,pcList,"1234567890987654","123456789098");

        TestStore testStore = new TestStore();

        boolean result = false;
        testStore.addTest(test);
        boolean expected = testStore.saveTest(test);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void hasTestExists() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType testType = new TestType("34567","assdf","swab", pcList, new ExternalModuleBloodWithoutKey());

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,pcList,"1234567890","123456789098");

        TestStore testStore = new TestStore();

        boolean result = false;
        testStore.addTest(test);
        boolean expected = testStore.hasTest(test);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void hasTestDoesNotExist() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));

        TestType testType = new TestType("34567","assdf","swab", pcList, new ExternalModuleBloodWithoutKey());

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,pcList,"1234567890","123456789098");

        TestStore testStore = new TestStore();

        boolean result = true;
        boolean expected = testStore.hasTest(test);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void getDiagnosedTests(){
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        List<Parameter> pList = new ArrayList<>();
        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
        pList.add(parameter1);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());


        app.domain.model.Test test = new app.domain.model.Test(testType, pList, pcList, "1123456789", "123456781234");
        test.setChemicalAnalysisDateTime(new Date());
        test.setDiagnosisDateTime(new Date());
        test.generateCode();

        TestStore testStore = new TestStore();

        test.setStatus(app.domain.model.Test.Status.Reported);
        testStore.saveTest(test);

        assertEquals(test, testStore.getDiagnosedTests().get(0));

    }

    @Test
    public void getTestByCode(){
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        List<Parameter> pList = new ArrayList<>();
        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
        pList.add(parameter1);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());


        app.domain.model.Test testExpected = new app.domain.model.Test(testType, pList, pcList, "1123456789", "123456781234");
        testExpected.setChemicalAnalysisDateTime(new Date());
        testExpected.setDiagnosisDateTime(new Date());
        testExpected.generateCode();

        TestStore testStore = new TestStore();

        testExpected.setStatus(app.domain.model.Test.Status.Reported);
        testStore.saveTest(testExpected);

        app.domain.model.Test testResult = testStore.getTestByCode(testExpected.getCode());

        assertEquals(testExpected, testResult);
    }

    @Test
    public void validateTest(){
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        List<Parameter> pList = new ArrayList<>();
        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
        pList.add(parameter1);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());


        app.domain.model.Test test = new app.domain.model.Test(testType, pList, pcList, "1123456789", "123456781234");
        test.generateCode();

        TestStore testStore = new TestStore();
        testStore.saveTest(test);

        ReportStore reportStore = App.getInstance().getCompany().getReportStore();

        reportStore.saveReport("Report", test.getCode());
        reportStore.validateReport(test.getCode());

        assertTrue(testStore.validateTest(test));

    }
}