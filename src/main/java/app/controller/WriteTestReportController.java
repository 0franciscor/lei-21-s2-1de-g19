package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterResult;
import app.domain.model.Test;
import auth.domain.store.ReportStore;
import auth.domain.store.TestStore;
import auth.mappers.dto.TestDto;

import java.util.List;

import static auth.mappers.TestMapper.toDto;

public class WriteTestReportController {
    private Company company;
    private TestStore testStore;
    private ReportStore reportStore;
    public WriteTestReportController() {
        this.company = App.getInstance().getCompany();
    }


    public List<TestDto> getAnalyzedTests() {
        testStore = company.getTestStore();
        List<Test> analyzedTestsList = testStore.getAnalyzedTests();
        List<TestDto> analyzedTestsListDto = toDto(analyzedTestsList);
        return analyzedTestsListDto;
    }



    public List<ParameterResult> getValues(TestDto test){
        return test.getValues();
    }


    public boolean saveReport(TestDto test ,String reportTxt) {
        String testCode = test.getTestCode();
        reportStore = company.getReportStore();
        if(reportStore.saveReport(reportTxt, testCode))
            return true;
        return false;
    }


}
