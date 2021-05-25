package app.controller;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CreateTestTypeControllerTest class, which is responsible for testing the CreateTestTypeController.
 *
 * @author Francisco Redol (1201239)
 */
public class CreateTestTypeControllerTest {

    @Test
    public void createTestType() {
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        boolean ttTest = ttControllerTest.createTestType("test0", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        assertTrue(ttTest);
    }

    @Test
    public void saveTestType() {
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        boolean ttTest = ttControllerTest.saveTestType();
        assertFalse(ttTest);
    }

    @Test
    public void getAllTestTypes() {
        CreateTestTypeController ttControllerTest = new CreateTestTypeController();
        TestType tt1 = new TestType("test0", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        TestType tt2 = new TestType("test1", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));

        ttControllerTest.createTestType("test0", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        ttControllerTest.saveTestType();
        ttControllerTest.createTestType("test1", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        ttControllerTest.saveTestType();

        List<TestType> testTypesExpected = new ArrayList<>();

        testTypesExpected.add(tt1);
        testTypesExpected.add(tt2);

        List<TestType> testTypesResult = ttControllerTest.getAllTestTypes();

        Assert.assertEquals(testTypesExpected.toString(), testTypesResult.toString());

    }
}