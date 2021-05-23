package auth.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestStoreTest {

    @Test
    public void saveTestInStore() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654");

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

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654");

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

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654");

        TestStore testStore = new TestStore();

        boolean result = false;
        testStore.addTest(test);
        boolean expected = testStore.validateTest(test);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void validateTestDoesNotExist() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654");

        TestStore testStore = new TestStore();

        boolean result = true;
        boolean expected = testStore.validateTest(test);

        Assert.assertEquals(result,expected);
    }
}