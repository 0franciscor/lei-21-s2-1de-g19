package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * LabCUI class, which is the interface which contains the laboratory coordinator options to choose.
 *
 * @author Francisco Redol (1201239)
 */
public class LabCUI implements Runnable {

    public LabCUI(){}

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Validate Tests", new ValidateTestUI()));
        options.add(new MenuItem("Consult historical tests from client", new ConsultTestDetailsByClientUI()));
        options.add(new MenuItem("Company Performance",new PerformanceUI()));
        options.add(new MenuItem("Import Tests From a .csv File", new ImportTestUI()));


        int option = 0;
        do
        {
            boolean exceptionThrown = false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nLaboratory Coordinator Menu:");
            } catch (Exception e) {
                System.out.println("\nUnavailable option.");
                exceptionThrown = true;
            }

            if ( (option >= 0) && (option < options.size()) && !exceptionThrown)
            {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
