package auth.mappers.dto;

import app.domain.model.Sample;

import java.util.ArrayList;
import java.util.Date;

public class TestDto {
    private String description;
    private String testType;
    private String code;
    private Date registrationDateTime;
    private Date chemicalAnalysisDateTime;
    private Date diagnosisDateTime;
    private ArrayList<Sample> listSamples;

    public TestDto(String description, String testType, String code) {
        this.description = description;
        this.testType = testType;
        this.code = code;
    }

    public TestDto(Date registrationDateTime, Date chemicalAnalysisDateTime, Date diagnosisDateTime, String code) {
        this.registrationDateTime = registrationDateTime;
        this.chemicalAnalysisDateTime = chemicalAnalysisDateTime;
        this.diagnosisDateTime = diagnosisDateTime;
        this.code = code;
    }

    public TestDto(String code){
        this.code=code;
    }

    public TestDto(ArrayList<Sample> listSample){
        this.listSamples=listSample;
    }

    public String toString() {
        return String.format("This is a %s test and the type of test is %s.", description, testType);
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

    public ArrayList<Sample> getListSamples(){
        return this.listSamples;
    }
}
