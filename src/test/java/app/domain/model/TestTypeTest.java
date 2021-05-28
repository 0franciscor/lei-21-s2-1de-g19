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
    public void setCodeIncorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test = new TestType("", "Covid_test", "Swab", pcList, new ExternalModuleBloodWithoutKey());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDescriptionIncorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test2 = new TestType("c0vid", "", "Swab", pcList, new ExternalModuleBloodWithoutKey());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCollectingMethodIncorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test3 = new TestType("c0vid", "Covid_test", "", pcList, new ExternalModuleBloodWithoutKey());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setParameterCategoriesListEmpty() {
        List<ParameterCategory> pcList = new ArrayList<>();
        TestType test5 = new TestType("c0vid", "Covid_test", "", pcList, new ExternalModuleBloodWithoutKey());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setExternalModuleIncorrect(){
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test3 = new TestType("c0vid", "Covid_test", "", pcList, null);
    }

    //Correct Tests

    @Test
    public void setCodeCorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test = new TestType("test1", "Covid_test", "Swab", pcList, new ExternalModuleBloodWithoutKey());
    }

    @Test
    public void setDescriptionCorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test2 = new TestType("test2", "blood_test", "Syringe", pcList, new ExternalModuleBloodWithoutKey());
    }

    @Test
    public void setCollectingMethodCorrect() {
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test3 = new TestType("test3", "urine_test", "tube", pcList, new ExternalModuleBloodWithoutKey());
    }

    @Test
    public void setParameterCategoriesListCorrect() {
        List<ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("pee","test0"));
        TestType test4 = new TestType("test4", "skin_test", "skinExtract", pcList, new ExternalModuleBloodWithoutKey());

    }

    @Test(expected = IllegalArgumentException.class)
    public void setExternalModuleCorrect(){
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType test3 = new TestType("c0vid", "Covid_test", "", pcList, new ExternalModuleBloodWithoutKey());
    }
}