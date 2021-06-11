package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.model.TestParameterResult;
import auth.domain.store.ReportStore;
import auth.domain.store.TestStore;
import auth.mappers.dto.TestDto;

import java.util.List;

import static auth.mappers.TestMapper.toDto;

/**
 * The Write Test Report Controller, which handles the WriteTestReportUI events
 *
 * @author Alexandre Soares
 */
public class WriteTestReportController {

    /**
     * The Controller's Company.
     */
    private Company company;

    /**
     * The Controller's Test Store.
     */
    private TestStore testStore;

    /**
     * The Controller's Report Store.
     */
    private ReportStore reportStore;

    /**
     *  The Controller's constructor, which fetches the Company Object
     */
    public WriteTestReportController() {
        this.company = App.getInstance().getCompany();
        this.testStore = company.getTestStore();
    }


    /**
     * A method responsible for fetching a list of analyzed tests.
     *
     * @return a List of Analyzed tests
     */
    public List<TestDto> getAnalyzedTests() {
        testStore = company.getTestStore();
        List<Test> analyzedTestsList = testStore.getAnalyzedTests();
        List<TestDto> analyzedTestsListDto = toDto(analyzedTestsList);
        return analyzedTestsListDto;
    }

    /**
     * @param test which contains the test Values.
     *
     * Method responsible for retrieving a test's values.
     *
     * @return the values
     */
    public List<TestParameterResult> getValues(TestDto test){
        return test.getParameterResults();
    }

    /**
     * @param test that they want to be saved.
     * @param reportTxt the test's Report.
     *
     * The method responsible for saving the report.
     *
     * @return the success of the operation.
     */
    public boolean saveReport(TestDto test ,String reportTxt) {
        String testCode = test.getTestCode();
        reportStore = company.getReportStore();
        if(reportStore.saveReport(reportTxt, testCode)) {
            company.getTestStore().getTestByCode(testCode).addReport(company.getReportStore().getReport(testCode));
            company.getTestStore().getTestByCode(testCode).updateDiagnosisDateTime();
            return true;
        }
        return false;
    }
}