package app.controller;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegisterTestControllerTest {

    @Test
    public void saveTestTrue() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654");

        RegisterTestController rtc = new RegisterTestController();

        boolean result = true;
        boolean expected = rtc.saveTest(test);

        Assert.assertEquals(result,expected);

    }

    @Test
    public void saveTestFalse() {

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        app.domain.model.Test test = new app.domain.model.Test(testType,parameters,"1234567890987654");

        RegisterTestController rtc = new RegisterTestController();


        boolean result = false;
        rtc.testStore.addTest(test);
        boolean expected = rtc.saveTest(test);

        Assert.assertEquals(result,expected);
    }
}