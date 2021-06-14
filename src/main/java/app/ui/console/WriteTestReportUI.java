package app.ui.console;

import app.controller.WriteTestReportController;
import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.ui.console.utils.Utils;
import auth.mappers.dto.TestDto;

import java.util.List;

/**
 * The Write Test Report UI
 *
 * @author Alexandre Soares
 */
public class WriteTestReportUI implements Runnable {
    private WriteTestReportController ctrl;
    public WriteTestReportUI() { ctrl = new WriteTestReportController(); }

    public void run(){
        try {
            int exit = 1;
            do {
                List<TestDto> listDto = ctrl.getAnalyzedTests();
                System.out.println("------ List of Tests available do to diagnosis report. ------");
                for (int x = 1 ; x <= listDto.size(); x++)
                    System.out.println("Test (" + x + ")");
                int opt = Utils.readIntegerFromConsole("Please choose a test.") - 1;
                TestDto test = listDto.get(opt);
                if (test == null)
                    exit = 0;
                System.out.println("-------- Test Results. --------");
                for (TestParameter c : test.getTestParameterList())
                    System.out.println(c.toString());
                int confirmation;
                String report;
                do {
                    do {
                        report = Utils.readLineFromConsole("Write your diagnosis report:");
                        confirmation = Utils.readIntegerFromConsole(String.format("Are you sure you about your report? 1-YES 2-NO \n %s", report));
                    } while (confirmation != 1 && confirmation != 2);
                    if(confirmation == 1) {
                        if(ctrl.saveReport(test, report))
                            System.out.println("Report completed successfully.");
                    }
                } while (confirmation != 1);
            } while (exit != 0);
        } catch (Exception e){
            System.out.println("There was an error when trying to write a test report.");
            return;
        }
    }
}
