package app.domain.model;

import app.controller.App;
import auth.domain.store.ClientStore;

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
    private Client client;
    private Company company;
    private ClientStore clientStore;

    public Test() { }

    public Test (TestType testType, List<Parameter> parameters, String citizenID ){

        this.company = App.getInstance().getCompany();
        this.testType = testType;
        this.parameters = parameters;
        this.clientStore = company.getClientStore();
        this.client = clientStore.getClient(citizenID);
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

    public String getStatus() { return status;  }
    public String getValues() { return values;  }
}
