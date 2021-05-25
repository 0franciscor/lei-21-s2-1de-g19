package auth.domain.store;

import app.domain.model.ParameterCategory;
import org.junit.Test;
import auth.domain.store.ParameterCategoryStore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        pcStoreTest.addParameterCategory(pc1);
        assertEquals(pc1.toString(), pcStoreTest.getParameterCategoryByCode(pc1.getCode()).toString());
    }

}