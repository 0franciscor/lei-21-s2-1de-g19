package app.controller;

import app.domain.model.Company;
import app.domain.model.NHSReport;
import app.domain.model.Test;
import app.ui.console.utils.Utils;
import auth.domain.store.TestStore;
import com.nhs.report.Report2NHS;

import javax.rmi.CORBA.Util;
import java.text.ParseException;
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
     * The array which contains the number of daily performed tests, according to the chosen date.
     */
    private double[] arrayY;

    /**
     * The Controller's String which contains the report.
     */
    private String data;

    /**
     * Builds a SendReportController without receiving parameters.
     */
    public SendReportController (){
        this.app = App.getInstance();
        this.company = app.getCompany();
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

        this.arrayY = new double[lstDateExceptSundays.size()];

        for (int i=0; i<lstAllTestWithResultCovidPositive.length; i++){
            Date date = lstDateExceptSundays.get(i);
            lstAllTestWithResultCovidPositive[i] = store.getAllTestWithResultCovidPositive(date);
            arrayY[i] = store.getDailyPerformedTests(date);
        }

        return Utils.verifyIfListsEmpty(lstAllTestWithResultCovidPositive);
    }

    /**
     * @param userIntention if true, it means the User selects dailyNumberTests as arrayX variable. If false, it means the user chose meanAge as arrayX variable.
     *
     * Method that is responsible for saving the data String on the controller, which will later be used to write the report through the API.
     */
    public void SimpleLinearRegression(boolean userIntention) throws ParseException {
        double[] arrayX;
        if(userIntention)
            arrayX = testStore.getDailyPositiveTests(lstAllTestWithResultCovidPositive);
        else
            arrayX = testStore.getMeanAgeFromList(lstAllTestWithResultCovidPositive);

        NHSReport report = company.generateNHSReport();

        this.data = report.calculateData(arrayX, arrayY);

    }

    /**
     * Method responsible for invoking the writeUsingFileWriter and writing a text report.
     */
    public void generateNHSReport (){
        Report2NHS.writeUsingFileWriter(data);
    }
}
