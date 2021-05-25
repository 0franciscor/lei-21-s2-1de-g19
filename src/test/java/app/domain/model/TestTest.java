package app.domain.model;

import app.controller.RegisterTestController;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTest {

    @Test
    public void checknhsCodeRulesValid() {

        app.domain.model.Test test = new app.domain.model.Test();

        String resultNhsCode = "123456789098";
        test.checknhsCodeRules(resultNhsCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checknhsCodeRulesInvalidBlank() {

        app.domain.model.Test test = new app.domain.model.Test();

        String resultNhsCode = "";
        test.checknhsCodeRules(resultNhsCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checknhsCodeRulesInvalidLength() {

        app.domain.model.Test test = new app.domain.model.Test();

        String resultNhsCode = "1234";
        test.checknhsCodeRules(resultNhsCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checknhsCodeRulesInvalidLettersNumbers() {

        app.domain.model.Test test = new app.domain.model.Test();

        String resultNhsCode = "123456-890uh";
        test.checknhsCodeRules(resultNhsCode);
    }

    @Test
    public void generateCode() {

        Company company = new Company("Many Labs");

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        TestType testType = new TestType("34567","assdf","swab", parameterCategory);

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);

        int codeLengthResult = 12;

        String code = company.getTestStore().createTest(testType,parameters,"1234567890987654", "123456789098").generateCode();
        int codeLengthExpected = code.length();

        assertEquals(codeLengthResult,codeLengthExpected);
    }
}