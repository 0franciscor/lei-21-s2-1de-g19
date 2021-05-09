package app.controller;

import app.domain.model.ParameterCategory;
import auth.domain.store.ParameterCategoryStore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CreateParameterCategoryControllerTest class, which is responsible for testing the CreateParameterCategoryController.
 *
 * @author Francisco Redol (1201239)
 */
public class CreateParameterCategoryControllerTest {

    @Test
    public void createParameterCategory() {
        CreateParameterCategoryController pcControllerTest = new CreateParameterCategoryController();
        boolean pcTest = pcControllerTest.createParameterCategory("CovidTest", "c0vid");
        assertEquals(true, pcTest);

    }

    @Test
    public void saveParameterCategory() {
        CreateParameterCategoryController pcControllerTest = new CreateParameterCategoryController();
        boolean pcTest = pcControllerTest.saveParameterCategory();
        assertEquals(false, pcTest);
    }

    @Test
    public void getParameterCategoryByCode() {
        CreateParameterCategoryController pcControllerTest = new CreateParameterCategoryController();
        pcControllerTest.createParameterCategory("covidTest", "covid");
        pcControllerTest.saveParameterCategory();
        ParameterCategory pcExpected = new ParameterCategory("covidTest", "covid");
        assertEquals(pcExpected, pcControllerTest.getParameterCategoryByCode("covid"));

    }

}