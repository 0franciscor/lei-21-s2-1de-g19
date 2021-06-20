package auth.domain.store;

import app.domain.shared.ExternalModuleBloodWithoutKey;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * TestTypeStoreTest class, which is responsible for testing the TestTypeStore.
 *
 * @author Francisco Redol (1201239)
 */
public class TestTypeStoreTest {

    @Test
    public void createTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        assertEquals(tt1.toString(), ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey()).toString());
    }

    @Test
    public void saveTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        assertTrue(ttStoreTest.saveTestType(tt1));
    }

    @Test
    public void DoesNotSaveTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        ttStoreTest.addTestType(tt1);
        assertTrue(ttStoreTest.saveTestType(tt1));
    }

    @Test
    public void validateTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        assertTrue(ttStoreTest.validateTestType(tt1));
    }

    @Test
    public void addTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", pcList, new ExternalModuleBloodWithoutKey());
        assertTrue(ttStoreTest.addTestType(tt1));
    }
}