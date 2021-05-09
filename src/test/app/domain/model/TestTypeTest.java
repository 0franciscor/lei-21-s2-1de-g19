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
}