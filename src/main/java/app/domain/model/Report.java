package app.domain.model;

import app.controller.App;
import auth.domain.store.ReportStore;
import auth.domain.store.TestStore;
import org.apache.commons.lang3.StringUtils;

public class Report {
    private Company company;
    private String diagnosisReport;
    private String testCode;


    public Report(String report, String testcode){
        this.company= App.getInstance().getCompany();
        this.testCode = testcode;
        checkReportRules(report);
        this.diagnosisReport = report;

    }
    public void checkReportRules (String report) {
        if (StringUtils.isBlank(report))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (report.length() > 400)
            throw new IllegalArgumentException("Name must have a maximum of 400 characters.");
    }
    public String getTestCode() {
        return testCode;
    }
}
