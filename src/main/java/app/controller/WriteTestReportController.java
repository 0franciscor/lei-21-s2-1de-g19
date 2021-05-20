package app.controller;

import app.domain.model.Company;
import app.domain.model.Report;
import app.domain.model.Test;
import auth.domain.store.TestStore;
import auth.mappers.dto.TestDto;

import java.util.List;

import static auth.mappers.TestMapper.testMapperToDto;

public class WriteTestReportController {
    private Company company;
    private TestStore testStore;
    private Report rep;
    public WriteTestReportController() {
        this.company = App.getInstance().getCompany();
        this.testStore = company.getTestStore();
    }
    public List<Test> getAnalyzedTests() {
        List<Test> analyzedTestsList = testStore.getAnalyzedTest();
        List<TestDto> analyzedTestsListDto = testMapperToDto(analyzedTestsList);
        return testStore.getAnalyzedTest();
    }
    public List<Test> getReferenceValues(Test test){

    }
    public boolean saveReport(String report,String testcode) {
        if(rep.saveReport(report, testcode))
            return true;
        return false;
    }
}
