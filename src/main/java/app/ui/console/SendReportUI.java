package app.ui.console;

import app.controller.SendReportController;
import app.domain.model.Test;
import app.ui.console.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents the send Report UI.
 *
 * @author Eduardo Gonçalves
 */
public class SendReportUI implements Runnable {

    /**
     * Send Report Controller.
     */
    private SendReportController ctrl;

    /**
     * Allows access to send report controller methods.
     */
    public SendReportUI (){
         ctrl = new SendReportController();
    }

    /**
     * It allows you to enter the data necessary to generate and send a report, make the confirmation and see if the operation was successful or not.
     */
    public void run() {

        System.out.printf("\n--- Requested data to send the covid-19 report to the NHS ---");
        Date date = Utils.readDateFromConsole("\nDate to see the report:");

        /*
        System.out.println("\n--- INTERVAL OF DATES ---");
        Date date1 = Utils.readDateFromConsole("Inicial date:");
        Date date2 = Utils.readDateFromConsole("Final date:");

         */

        System.out.println("\n--- NUMBER OF HISTORICAL POINTS ---");

        List<String> options = new ArrayList<>();
        options.add("Days");
        options.add("Weeks");

        int option = Utils.showAndSelectIndex(options,"\nSelect an option from the list.");

        int histPoints;

        if (option == 0){
            histPoints = Utils.readIntegerFromConsole("Number of days:");

        }
        else {
            histPoints = Utils.readIntegerFromConsole("Number of weeks:");
            histPoints = histPoints * 7;
        }

        System.out.println("\n--- REGRESSION MODEL ---");

        List<String> options2 = new ArrayList<>();
        options2.add("Simple linear regression model");
        options2.add("Multilinear regression model");

        int option3 = Utils.showAndSelectIndex(options2,"\nSelect an option from the list.");

        boolean userInteraction = false;
        if (option3 == 0) {

            System.out.println("\n--- SELECT BETWEEN DAILY NUMBER TESTS AND MEAN AGE ---");

            List<String> options4 = new ArrayList<>();
            options4.add("Daily Number tests");
            options4.add("Mean age");

            int option5 = Utils.showAndSelectIndex(options4,"\nSelect an option from the list.");

            if (option5 == 0){
                userInteraction = true;

            } else {
                userInteraction = false;
            }

        }

        boolean hypTest = false;

        System.out.println("\n--- hypothesis test ---");

        if (option3 == 0){

            List<String> option6 = new ArrayList<>();
            option6.add("a");
            option6.add("b");

            int option7 = 0;
            try {
                 option7 = Utils.showAndSelectIndex(option6,"\nSelect an option from the list.");
            } catch (Exception e){
                System.out.println("Invalid option");
                return;
            }

            if (option7 == 0){
                hypTest = true;

            } else {
                hypTest = false;
            }
        }

        double sigLevel = Utils.readDoubleFromConsole("Significance level:");

        double confLevel = Utils.readDoubleFromConsole("Confidence level:");


        try {
            ctrl.getAllTestWithResultCovidPositive(date, histPoints);
        } catch (Exception e) {
            System.out.println("\nThere aren't any test with result covid positive list at the moment.");
        }

        if (option3 == 0) {

            try {
                ctrl.SimpleLinearRegression(userInteraction, sigLevel,confLevel, hypTest);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {

            try {
                ctrl.MultilinearRegression(sigLevel,confLevel);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        ctrl.sendNHSReport();
    }
}
