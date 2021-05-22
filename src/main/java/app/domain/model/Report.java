package app.domain.model;

import app.controller.App;
import auth.domain.store.ReportStore;
import auth.domain.store.TestStore;
import org.apache.commons.lang3.StringUtils;

public class Report {
    private Company company;
    private TestStore testStore;
    private ReportStore reportStore;
    private String diagnosisReport;
    private Test test;


    public Report(String report, String testcode){
        this.company= App.getInstance().getCompany();
        this.testStore = company.getTestStore();
        this.reportStore = company.getReportStore();
        checkReportRules(report);
        this.diagnosisReport = report;
        //this.test = testStore.getTestByCode(testcode);
    }
    public boolean validateReport(String report, String testcode) {
        Report diagnosisReport = new Report(report, testcode);
        if (reportStore.addReport(diagnosisReport))
            return true;
        return false;

    }
    public boolean saveReport(String report, String testcode) {
        if(validateReport(report, testcode))
            return true;
        return false;
    }
    public void checkReportRules (String report) {
        if (StringUtils.isBlank(report))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (report.length() > 400)
            throw new IllegalArgumentException("Name must have a maximum of 400 characters.");
    }
}
