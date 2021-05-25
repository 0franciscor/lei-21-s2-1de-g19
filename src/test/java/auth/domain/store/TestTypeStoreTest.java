package auth.domain.store;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Test;

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
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        assertEquals(tt1.toString(), ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", new ParameterCategory("test", "test0")).toString());
    }

    @Test
    public void saveTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        assertTrue(ttStoreTest.saveTestType(tt1));
    }

    @Test
    public void DoesNotSaveTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        ttStoreTest.addTestType(tt1);
        assertFalse(ttStoreTest.saveTestType(tt1));
    }

    @Test
    public void validateTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        assertTrue(ttStoreTest.validateTestType(tt1));
    }

    @Test
    public void addTestType() {
        TestTypeStore ttStoreTest = new TestTypeStore();
        TestType tt1 = ttStoreTest.createTestType("test1", "testJunit", "TestjUnit", new ParameterCategory("test", "test0"));
        assertTrue(ttStoreTest.addTestType(tt1));
    }
}