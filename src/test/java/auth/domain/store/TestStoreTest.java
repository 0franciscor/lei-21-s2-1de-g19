package auth.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class TestStoreTest {

    @Test
    public void createTest (){

        /*
        TestStore testStore = new TestStore();

        Client cl = new Client("1234567890987654","1234567890","12/12/1940","male","1234567890","12345678901","roberto@gmail.com","Roberto");

        ParameterCategory parameterCategory = new ParameterCategory("rfgvb","45678");

        TestType testType = new TestType("12345", "cvfrt", "derfc", parameterCategory);
        List<Parameter> parameterList = new ArrayList<>();

        Parameter parameter = new Parameter("69870","fcvbn","yuhbc", parameterCategory);
        parameterList.add(parameter);

        app.domain.model.Test test = testStore.createTest(testType,parameterList, cl.getCitizenID());

        assertEquals(test.toString(),testStore.createTest(testType, parameterList,cl.getCitizenID()));

         */

    }

    @Test
    public void saveTestInStore() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654","123456789098");

        TestStore testStore = new TestStore();

        boolean result = true;
        boolean expected = testStore.saveTest(test);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void DoesNotsaveTestInStore() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654","123456789098");

        TestStore testStore = new TestStore();

        boolean result = false;
        testStore.addTest(test);
        boolean expected = testStore.saveTest(test);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void validateTestExists() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654","123456789098");

        TestStore testStore = new TestStore();

        boolean result = false;
        testStore.addTest(test);
        boolean expected = testStore.hasTest(test);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void validateTestDoesNotExist() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654","123456789098");

        TestStore testStore = new TestStore();

        boolean result = true;
        boolean expected = testStore.hasTest(test);

        Assert.assertEquals(result,expected);
    }
}