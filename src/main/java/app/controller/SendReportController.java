package app.controller;

import app.domain.model.Company;
import app.domain.model.NHSReport;
import app.domain.model.Test;
import auth.domain.store.TestStore;
import com.nhs.report.Report2NHS;

import java.util.List;

/**
 * Represents the controller that serves at intermediary between the UI and the domain layer.
 *
 * @author Eduardo Gonçalves
 */
public class SendReportController {

    /**
     * The controller's App object.
     */
    private App app;

    /**
     *  The controller's Company.
     */
    private Company company;

    /**
     * The controller's Test store.
     */
    public TestStore testStore;

    /**
     * Builds a SendReportController without receiving parameters.
     */
    public  SendReportController (){
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.testStore = company.getTestStore();

    }


    /**
     * Creates a list which is able to return a copy of all tests that have the result Covid positive.
     *
     * @return a list which is able to return a copy of all tests that have the result Covid positive.
     */
    public List<Test> getAllTestWithResultCovidPositive(){
        TestStore store = company.getTestStore();
        List<Test> listTestWithResultCovidPositive = store.getAllTestWithResultCovidPositive();

        return listTestWithResultCovidPositive;
    }

    /**
     * Generates a covid-19 report that will be sent to the NHS.
     *
     * @param listTestWithResultCovidPositive list with tests that have result covid positive
     */
    public void generateNHSReport (List<Test> listTestWithResultCovidPositive){

        NHSReport report = company.generateNHSReport(listTestWithResultCovidPositive);
        String data = report.calculateData();
        Report2NHS.writeUsingFileWriter(data);

    }


    /*
    public boolean saveNHSReport (NHSReport nhsReport){

        nhsReport.validateNHSReport();


    }

     */
}
