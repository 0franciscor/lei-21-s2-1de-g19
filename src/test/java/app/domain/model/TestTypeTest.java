package app.domain.model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * TestTypeTest class, which is responsible for testing the TestType.
 *
 * @author Francisco Redol (1201239)
 */
public class TestTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void setCode() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test = new TestType("", "Covid_test", "Swab", pcList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDescription() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test2 = new TestType("c0vid", "", "Swab", pcList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCollectingMethod() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test3 = new TestType("c0vid", "Covid_test", "", pcList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setParameterCategoriesList() {
        List<ParameterCategory> pcList = new ArrayList<>();
        TestType test5 = new TestType("c0vid", "Covid_test", "", pcList);
    }

    //Correct Tests

    @Test
    public void setCodeCorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test = new TestType("test1", "Covid_test", "Swab", pcList);
    }

    @Test
    public void setDescriptionCorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test2 = new TestType("test2", "blood_test", "Syringe", pcList);
    }

    @Test
    public void setCollectingMethodCorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test3 = new TestType("test3", "urine_test", "tube", pcList);
    }

    @Test
    public void setParameterCategoriesListCorrect() {
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        TestType test4 = new TestType("test4", "skin_test", "skinExtract", pcList);

    }
}