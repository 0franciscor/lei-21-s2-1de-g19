package auth.domain.store;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ParameterCategoryStoreTest class, which is responsible for testing the ParameterCategoryStore.
 *
 * @author Francisco Redol (1201239)
 */
public class ParameterCategoryStoreTest {

    @Test
    public void createParameterCategory() {
        ParameterCategoryStore pcStoreTest = new ParameterCategoryStore();
        ParameterCategory pc1 = pcStoreTest.createParameterCategory("test", "test0");
        assertEquals(pc1.toString(), pcStoreTest.createParameterCategory("test", "test0").toString());
    }

    @Test
    public void saveParameterCategory() {
        ParameterCategoryStore pcStoreTest = new ParameterCategoryStore();
        ParameterCategory pc1 = pcStoreTest.createParameterCategory("test", "test0");
        assertTrue(pcStoreTest.saveParameterCategory(pc1));
    }

    @Test
    public void validateParameterCategory() {
        ParameterCategoryStore pcStoreTest = new ParameterCategoryStore();
        ParameterCategory pc1 = pcStoreTest.createParameterCategory("test", "test0");
        assertTrue(pcStoreTest.validateParameterCategory(pc1));
    }

    @Test
    public void addParameterCategory() {
        ParameterCategoryStore pcStoreTest = new ParameterCategoryStore();
        ParameterCategory pc1 = pcStoreTest.createParameterCategory("test", "test0");
        assertTrue(pcStoreTest.addParameterCategory(pc1));
    }

    @Test
    public void getParameterCategoryByCode() {
        ParameterCategoryStore pcStoreTest = new ParameterCategoryStore();
        ParameterCategory pc1 = pcStoreTest.createParameterCategory("test", "test0");
        pcStoreTest.saveParameterCategory(pc1);
        assertEquals(pc1.toString(), pcStoreTest.getParameterCategoryByCode(pc1.getCode()).toString());
    }
}