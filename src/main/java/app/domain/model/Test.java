package app.domain.model;

import java.util.List;

public class Test {

    private String description;
    private String code;
    private String nhsCode;
    private String collectDateTime;
    private String chemicalAnalysisDateTime;
    private String diagnosisDateTime;
    private String validationDateTime;
    private TestType testType;
    private List<Parameter> parameters;

    public Test (TestType testType, List<Parameter> parameters){

        this.testType = testType;
        this.parameters = parameters;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getNhsCode() {
        return nhsCode;
    }

    public String getCollectDateTime() {
        return collectDateTime;
    }

    public String getChemicalAnalysisDateTime() {
        return chemicalAnalysisDateTime;
    }

    public String getDiagnosisDateTime() {
        return diagnosisDateTime;
    }

    public String getValidationDateTime() {
        return validationDateTime;
    }

    public TestType getTestType() {
        return testType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}
