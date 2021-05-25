package app.controller;

import app.domain.model.Company;
import app.domain.model.Report;
import app.domain.model.Test;
import auth.domain.store.ReportStore;
import auth.domain.store.TestStore;
import auth.mappers.dto.TestDto;

import java.util.List;

import static auth.mappers.TestMapper.testMapperToDto;

public class WriteTestReportController {
    private Company company;
    private TestStore testStore;
    private ReportStore reportStore;
    public WriteTestReportController() {
        this.company = App.getInstance().getCompany();
    }

    /*
    public List<TestDto> getAnalyzedTests() {
        testStore = company.getTestStore();
        List<Test> analyzedTestsList = testStore.getAnalyzedTests();
        List<TestDto> analyzedTestsListDto = testMapperToDto(analyzedTestsList);
        return analyzedTestsListDto;
    }

     */
    /*
    public List<Test> getReferenceValues(Test test){

    }

     */
    public boolean saveReport(TestDto test ,String reportTxt) {
        String testCode = test.getTestCode();
        reportStore = company.getReportStore();
        if(reportStore.saveReport(reportTxt, testCode))
            return true;
        return false;
    }


}
