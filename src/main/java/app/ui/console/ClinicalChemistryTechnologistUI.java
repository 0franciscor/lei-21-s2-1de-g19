package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClinicalChemistryTechnologistUI implements Runnable{

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Record test result", new RecordTestResultUI()));


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
