package app.controller;

import app.domain.model.Company;
import app.domain.model.NHSReport;
import app.domain.model.Test;
import app.ui.console.utils.Utils;
import auth.domain.store.TestStore;
import com.nhs.report.Report2NHS;

import javax.rmi.CORBA.Util;
import java.util.Date;
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
     * The controller's list with positive covid tests.
     */
    private List<Test> [] lstAllTestWithResultCovidPositive;

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
    public boolean getAllTestWithResultCovidPositive(Date dateToSee, int histPoints){


        TestStore store = company.getTestStore();
        List<Date> lstDateExceptSundays = Utils.getDaysWithoutSundays(dateToSee, histPoints);
        this.lstAllTestWithResultCovidPositive = new List[lstDateExceptSundays.size()];
        for (int i=0; i<lstAllTestWithResultCovidPositive.length; i++){
            Date date = lstDateExceptSundays.get(i);
            lstAllTestWithResultCovidPositive[i] = store.getAllTestWithResultCovidPositive(date);
        }
        return Utils.verifyIfListsEmpty(lstAllTestWithResultCovidPositive);
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
