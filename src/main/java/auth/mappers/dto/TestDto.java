package auth.mappers.dto;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDto {

    private String description;
    private TestType testType;
    private String code;
    private String client;
    private List<ParameterCategory> parameterCategories;
    private List<Parameter> parameters;
    private List<TestParameter> testParameters;
    private List<TestParameterResult> parameterResultList;
    private Date registrationDateTime;
    private Date chemicalAnalysisDateTime;
    private Date diagnosisDateTime;
    private ArrayList<Sample> listSamples;
    private String TIN;

    private Report report;

    public TestDto(String description, TestType testType, String code, List<TestParameter> parameterList) {
        this.description = description;
        this.testParameters = parameterList;
        this.testType = testType;
        this.code = code;
    }

    public TestDto(Date registrationDateTime, Date chemicalAnalysisDateTime, Date diagnosisDateTime, String code) {
        this.registrationDateTime = registrationDateTime;
        this.chemicalAnalysisDateTime = chemicalAnalysisDateTime;
        this.diagnosisDateTime = diagnosisDateTime;
        this.code = code;
    }

    public TestDto(TestType testType, Date chemicalAnalysisDateTime, List<TestParameterResult> parameterResults, String code, String TIN){
        this.testType = testType;
        this.chemicalAnalysisDateTime = chemicalAnalysisDateTime;
        this.code = code;
        this.parameterResultList = parameterResults;
        this.TIN = TIN;
    }

    public TestDto(String code){this.code=code;}


    public TestDto(String c, TestType tt, List<ParameterCategory> lpc, List<Parameter> lp, String code){
        this.client=c;
        this.testType =tt;
        this.parameterCategories=lpc;
        this.parameters=lp;
        this.code=code;
    }

    public TestDto(List<Parameter> parameterList){
        this.parameters = parameterList;
    }

    /**
     * Builds a test in data transfer object that receives as parameters a list of samples.
     * @param listSample
     */
    public TestDto(ArrayList<Sample> listSample){
        this.listSamples=listSample;
    }
    public TestDto(TestType testType,Date chemicalAnalysisDateTime, List<TestParameterResult> parameterResults, String code, String tin, Report report){
        this.testType =testType;
        this.chemicalAnalysisDateTime = chemicalAnalysisDateTime;
        this.parameterResultList = parameterResults;
        this.code = code;
        this.report = report;
    }

    public String toString(int opt) {
        if(opt==0)
            return String.format("This is a %s test and the type of test is %s.", description, testType.getDescription());

        else
            return String.format("Test with: \nClient %s. \n%s. \n%s. \n%s", this.client,this.testType,this.parameterCategories,this.parameters);
    }

    public String getTestCode() {
        return code;
    }

    public Date getRegistrationDateTime(){
        return registrationDateTime;
    }

    public Date getChemicalAnalysisDateTime(){
        return chemicalAnalysisDateTime;
    }

    public Date getDiagnosisDateTime(){
        return diagnosisDateTime;
    }

    public String getCode(){
        return this.code;
    }

    public List<Parameter> getParameters(){
        return this.parameters;
    }

    public List<TestParameterResult> getParameterResults() {
        return parameterResultList;
    }

    public ArrayList<Sample> getListSamples(){
        return this.listSamples;
    }

    public String getTIN(){
        return this.TIN;
    }

    public TestType getTestType(){
        return this.testType;
    }

    public List<TestParameter> getTestParameterList(){
        return this.testParameters;
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        TestDto obj2 = (TestDto) obj;

        return this.registrationDateTime.equals(obj2.registrationDateTime) && this.chemicalAnalysisDateTime.equals(obj2.chemicalAnalysisDateTime)
                && this.diagnosisDateTime.equals(obj2.diagnosisDateTime);
    }
}
