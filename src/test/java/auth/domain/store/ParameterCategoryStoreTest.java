package auth.domain.store;

import app.domain.shared.ExternalModuleBloodWithoutKey;
import app.domain.model.ParameterCategory;
import auth.mappers.dto.TestTypeDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getAllParameterCategoriesByTestType (){

        ParameterCategoryStore parameterCategoryStore = new ParameterCategoryStore();

        List<ParameterCategory> listParameterCategories = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("cvfrt","78906");
        listParameterCategories.add(parameterCategory);

        TestTypeDto testTypeDto = new TestTypeDto("45678", "cvfgt", "tyghy", listParameterCategories, new ExternalModuleBloodWithoutKey());

        parameterCategoryStore.addParameterCategory(parameterCategory);

        List<ParameterCategory> parameterCategoryListResult = parameterCategoryStore.getAllParameterCategoriesByTestType(testTypeDto);

        Assert.assertEquals(parameterCategoryListResult.toString(),listParameterCategories.toString());

    }

}