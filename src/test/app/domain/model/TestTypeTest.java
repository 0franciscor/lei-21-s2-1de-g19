package app.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * TestTypeTest class, which is responsible for testing the TestType.
 *
 * @author Francisco Redol (1201239)
 */
public class TestTypeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void setCode() {
        TestType test = new TestType("", "Covid_test", "Swab", new ParameterCategory("test","test0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDescription() {
        TestType test2 = new TestType("c0vid", "", "Swab", new ParameterCategory("test","test0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCollectingMethod() {
        TestType test3 = new TestType("c0vid", "Covid_test", "", new ParameterCategory("test","test0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setParameterCategory() {
        TestType test4 = new TestType("c0vid", "Covid_test", "", new ParameterCategory("",""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setParameterCategoriesList() {
        List<ParameterCategory> testList = new ArrayList<>();
        testList.add(null);
        TestType test5 = new TestType("c0vid", "Covid_test", "", testList);
    }

    //Correct Tests

    @Test
    public void setCodeCorrect() {
        TestType test = new TestType("test1", "Covid_test", "Swab", new ParameterCategory("covid","c0vid"));
    }

    @Test
    public void setDescriptionCorrect() {
        TestType test2 = new TestType("test2", "blood_test", "Syringe", new ParameterCategory("hemogram","bl00d"));
    }

    @Test
    public void setCollectingMethodCorrect() {
        TestType test3 = new TestType("test3", "urine_test", "tube", new ParameterCategory("pee","test0"));
    }

    @Test
    public void setParameterCategoryCorrect() {
        TestType test4 = new TestType("test4", "skin_test", "skinExtract", new ParameterCategory("skinT","skin0"));
    }

    @Test
    public void setParameterCategoriesListCorrect() {
        List<ParameterCategory> testList = new ArrayList<>();
        ParameterCategory testPC= new ParameterCategory("pee","test0");
        ParameterCategory testPC2= new ParameterCategory("skinT","skin0");
        testList.add(testPC);
        testList.add(testPC2);
        TestType test4 = new TestType("test4", "skin_test", "skinExtract", new ParameterCategory("skinT","skin0"));
        test4.setParameterCategoriesList(testList);
    }
}