package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a new clinical analysis laboratory", new RegisterClinicalAnalysisLaboratoryUI()));
        options.add(new MenuItem("Register a new employee", new RegisterNewEmployeeUI()));
        options.add(new MenuItem("Specify a new parameter category", new CreateParameterCategoryUI()));
        options.add(new MenuItem("Specify a new parameter", new CreateParameterUI()));
        options.add(new MenuItem("Specify a new type of test", new CreateTestTypeUI()));
        options.add(new MenuItem("Send the covid-19 report to the NHS", new SendReportUI()));

        int option = 0;
        do
        {
            boolean exceptionThrown = false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");
            } catch (Exception e) {
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }

            if ( (option >= 0) && (option < options.size()) && !exceptionThrown)
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
