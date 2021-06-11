package app.ui.console;

import app.controller.SendReportController;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendReportUI implements Runnable {

    private SendReportController ctrl;

    public SendReportUI (){
         ctrl = new SendReportController();
    }

    public void run() {

        System.out.printf("\n--- Requested data to send the covid-19 report to the NHS ---");
        Date date = Utils.readDateFromConsole("\nDate to see the report:");

        System.out.println("\n--- INTERVAL OF DATES ---");
        Date date1 = Utils.readDateFromConsole("Inicial date:");
        Date date2 = Utils.readDateFromConsole("Final date:");

        System.out.println("\n--- NUMBER OF HISTORICAL POINTS ---");

        List<String> options = new ArrayList<>();
        options.add("Days");
        options.add("Weeks");

        int option = Utils.showAndSelectIndex(options,"\nSelect an option from the list.");

        int option1;

        if (option == 0)
            option1 = Utils.readIntegerFromConsole("Number of days:");
        else
            option1 = Utils.readIntegerFromConsole("Number of weeks:");


        System.out.println("\n--- REGRESSION MODEL ---");

        List<String> options2 = new ArrayList<>();
        options2.add("Simple linear regression model");
        options2.add("Multilinear regression model");

        int option3 = Utils.showAndSelectIndex(options2,"\nSelect an option from the list.");

        double sigLevel = Utils.readDoubleFromConsole("Significance level:");

    }
}
