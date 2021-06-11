package app.controller;

import app.domain.model.Company;
import app.domain.model.NHSReport;
import app.domain.model.Test;
import auth.domain.store.ClientStore;
import auth.domain.store.TestStore;
import com.nhs.report.Report2NHS;

import java.util.List;

public class SendReportController {

    private App app;
    private Company company;
    public TestStore testStore;

    public  SendReportController (){
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.testStore = company.getTestStore();

    }

    /*
    public List<Test> getAllTestWithResultCovidPositive(){
        TestStore store = company.getTestStore();
        List<Test> listTestWithResultCovidPositive = store.getAllTestWithResultCovidPositive();

        return listTestWithResultCovidPositive;

    }

     */

    /*
    public void generateNHSReport (List<Test> listTestWithResultCovidPositive){

        NHSReport report = company.generateNHSReport(listTestWithResultCovidPositive);
        String data = report.calculateData();
        Report2NHS.writeUsingFileWriter(data);

    }

     */

    /*
    public boolean saveNHSReport (NHSReport nhsReport){

        nhsReport.validateNHSReport();


    }

     */
}
