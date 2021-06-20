package app.controller;

import app.domain.model.Company;
import app.domain.model.NHSReport;
import app.domain.model.Test;
import app.ui.console.utils.Utils;
import auth.domain.store.TestStore;
import com.nhs.report.Report2NHS;

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
     * The List of dates (without sundays), according to the chosen dates.
     */
    private List<Date> lstDateExceptSundays;

    /**
     * The controller's list with positive covid tests.
     */
    private List<Test> [] lstAllTestWithResultCovidPositive;

    /**
     * The controller's list with covid tests.
     */
    private List<Test> [] lstAllTestWithResultCovid;

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
        this.testStore = company.getTestStore();
    }


    /**
     * Creates a list which is able to return a copy of all tests that have the result Covid positive.
     *
     * @return a list which is able to return a copy of all tests that have the result Covid positive.
     */
    public void getAllTestWithResultCovidPositive(Date dateToSee, int histPoints){
        TestStore store = company.getTestStore();

        this.lstDateExceptSundays = Utils.getDaysWithoutSundays(dateToSee, histPoints);
        this.lstAllTestWithResultCovidPositive = new List[lstDateExceptSundays.size()];

        this.arrayY = new double[lstDateExceptSundays.size()];

        for (int i=0; i<lstAllTestWithResultCovidPositive.length; i++){
            Date date = lstDateExceptSundays.get(i);
            lstAllTestWithResultCovidPositive[i] = store.getAllTestWithResultCovidPositive(date);
        }

        this.arrayY = store.getDailyPositiveTests(lstAllTestWithResultCovidPositive);
    }

    /**
     * @param userIntention if true, it means the User selects dailyNumberTests as arrayX variable. If false, it means the user chose meanAge as arrayX variable.
     * @param sigLevel the significance level chosen by the user.
     *
     * Method that is responsible for saving the data String on the controller, which will later be used to write the report through the API.
     */
    public void SimpleLinearRegression(boolean userIntention, double sigLevel,double confLevel, boolean hypTest) throws ParseException {
        double[] arrayX = new double[lstDateExceptSundays.size()];
        if(userIntention) {
            int i = 0;
            for(Date date : lstDateExceptSundays){
                arrayX[i] = testStore.getDailyPerformedTests(date);
                i++;
            }

        } else {

            TestStore store = company.getTestStore();

            this.lstAllTestWithResultCovid = new List[lstDateExceptSundays.size()];

            for (int i=0; i<lstAllTestWithResultCovid.length; i++){
                Date date = lstDateExceptSundays.get(i);
                this.lstAllTestWithResultCovid[i] = store.getAllTestWithResultCovid(date);
            }

            arrayX = store.getMeanAgeFromList(this.lstAllTestWithResultCovid);
        }

        NHSReport report = company.generateNHSReport(sigLevel, confLevel, hypTest);

        this.data = report.calculateDataLinear(arrayX, arrayY, lstDateExceptSundays);

    }

    public void MultilinearRegression (double sigLevel, double confLevel) throws ParseException {

        double[] dailyNumberTests = new double[lstDateExceptSundays.size()];

        int i = 0;
        for(Date date : lstDateExceptSundays){
            dailyNumberTests[i] = testStore.getDailyPerformedTests(date);
            i++;
        }

        TestStore store = company.getTestStore();

        this.lstAllTestWithResultCovid = new List[lstDateExceptSundays.size()];
        for (int j=0; j<lstAllTestWithResultCovid.length; j++){
            Date date = lstDateExceptSundays.get(j);
            this.lstAllTestWithResultCovid[j] = store.getAllTestWithResultCovid(date);
        }

        double [] meanAge = store.getMeanAgeFromList(this.lstAllTestWithResultCovid);

        double[][] BiarrayX = store.createBiarrayX(dailyNumberTests, meanAge);

        NHSReport report = company.generateNHSReport(sigLevel, confLevel);

        this.data = report.calculateDataMultiLinear(BiarrayX, arrayY, lstDateExceptSundays);


    }

    /**
     * Method responsible for invoking the writeUsingFileWriter and writing a text report.
     */
    public void sendNHSReport (){
        Report2NHS.writeUsingFileWriter(data);
    }
}
