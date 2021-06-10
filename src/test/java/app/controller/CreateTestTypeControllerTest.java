package app.controller;

import app.domain.model.*;
import app.domain.shared.ExternalModule;
import app.domain.shared.ExternalModuleBloodWithKey;
import app.domain.shared.ExternalModuleBloodWithoutKey;
import app.domain.shared.ExternalModuleCovid;
import auth.mappers.dto.TestTypeDto;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CreateTestTypeControllerTest class, which is responsible for testing the CreateTestTypeController.
 *
 * @author Francisco Redol (1201239)
 */
public class CreateTestTypeControllerTest {

    @Test
    public void createTestType() {
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        boolean ttTest = ttControllerTest.createTestType("test0", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        assertTrue(ttTest);
    }

    @Test
    public void saveTestType() {
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        boolean ttTest = ttControllerTest.saveTestType();
        assertFalse(ttTest);
    }

    @Test
    public void getAllTestTypesDto(){
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));

        TestTypeDto expected = new TestTypeDto("test0", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        ttControllerTest.createTestType("test0", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        ttControllerTest.saveTestType();

        TestTypeDto result = ttControllerTest.getAllTestTypesDto().get(0);

        assertEquals(result.toString(), expected.toString());
    }

    @Test
    public void getAllTestTypes() {
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        TestType tt1 = new TestType("test0", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        TestType tt2 = new TestType("test1", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());

        ttControllerTest.createTestType("test1", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        ttControllerTest.saveTestType();

        List<TestType> testTypesExpected = new ArrayList<>();

        testTypesExpected.add(tt1);
        testTypesExpected.add(tt2);

        List<TestType> testTypesResult = ttControllerTest.getAllTestTypes();

        Assert.assertEquals(testTypesExpected.toString(), testTypesResult.toString());

    }

    @Test
    public void getExternalModules() {
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        List<ExternalModule> externalModulesExpected = new ArrayList<>();
        externalModulesExpected.add(new ExternalModuleCovid());
        externalModulesExpected.add(new ExternalModuleBloodWithoutKey());
        externalModulesExpected.add(new ExternalModuleBloodWithKey());
        assertEquals(externalModulesExpected.toString(), ttControllerTest.getExternalModules().toString());
    }
}