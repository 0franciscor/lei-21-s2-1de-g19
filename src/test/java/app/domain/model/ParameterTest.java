package app.domain.model;

public class ParameterTest {

    @org.junit.Test
    public void checkCodeRulesValid() {
        Parameter parameter = new Parameter();

        String resultCode="abn3e";
        parameter.checkCodeRules(resultCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkCodeRulesInvalidBlank(){
        Parameter parameter = new Parameter();

        String resultCode="";
        parameter.checkCodeRules(resultCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkCodeRulesInvalidLength(){
        Parameter parameter = new Parameter();

        String resultCode="abcdef";
        parameter.checkCodeRules(resultCode);
    }

    @org.junit.Test
    public void checkDescriptionRulesValid() {
        Parameter parameter = new Parameter();

        String resultDescription="abn3e";
        parameter.checkDescriptionRules(resultDescription);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkDescriptionRulesInvalidBlank(){
        Parameter parameter = new Parameter();

        String resultDescription="";
        parameter.checkDescriptionRules(resultDescription);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkDescriptionRulesInvalidLength(){
        Parameter parameter = new Parameter();

        String resultDescription="abcdefghijklmnopqrstu";
        parameter.checkDescriptionRules(resultDescription);
    }

    @org.junit.Test
    public void checkDesignationRulesValid() {
        Parameter parameter = new Parameter();

        String resultDesignation="abn3e";
        parameter.checkDesignationRules(resultDesignation);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkDesignationRulesInvalidBlank(){
        Parameter parameter = new Parameter();

        String resultDesignation="";
        parameter.checkDesignationRules(resultDesignation);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkDesignationRulesInvalidLength(){
        Parameter parameter = new Parameter();

        String resultDesignation="abcdefghi";
        parameter.checkDesignationRules(resultDesignation);
    }
}