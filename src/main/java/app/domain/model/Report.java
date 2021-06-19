package app.domain.model;

import app.controller.App;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Report Class
 *
 * @author Alexandre Soares
 */
public class Report implements Serializable {

    /**
     * The Report's company.
     */
    private Company company;

    /**
     * The Report's string, containing information written by the Specialist Doctor.
     */
    private String diagnosisReport;

    /**
     * The Report's code (equal to the matching test code).
     */
    private String testCode;

    /**
     * The Report's validation state.
     */
    private boolean validation;


    /**
     * @param report String.
     * @param testcode Test code.
     *
     * The Report builder.
     */
    public Report(String report, String testcode){
        this.company= App.getInstance().getCompany();
        this.testCode = testcode;
        checkReportRules(report);
        this.diagnosisReport = report;
        this.validation = false;

    }

    /**
     * @param report the report's String.
     *
     * Method that checks the report's String rules.
     */
    public void checkReportRules (String report) {
        if (StringUtils.isBlank(report))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (report.length() > 400)
            throw new IllegalArgumentException("Name must have a maximum of 400 characters.");
    }

    /**
     * @return the test's code.
     */
    public String getTestCode() {
        return testCode;
    }

    /**
     * Is responsible for informing about the validation state of a certain Report object.
     *
     * @return the current validation state
     */
    public boolean getValidation() {
        return validation;
    }

    /**
     * Responsible for changing the validation state of a Report object.
     *
     * @param validation is the new validation state for a report
     */
    public void setValidation(boolean validation){
        this.validation = validation;
    }
}
