package app.ui.console;


import app.controller.WriteTestReportController;
import app.domain.model.ParameterResult;
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
                ctrl.setAnalyzed();
                List<TestDto> listDto = ctrl.getAnalyzedTests();
                System.out.println("------ List of Tests available do to diagnosis report. ------");
                for(int i=0; i<listDto.size();i++){
                    System.out.println(i+1+". "+listDto.get(i).toString(0));
                }
                TestDto test = (TestDto) Utils.selectsObject(listDto);
                String value = "";
                for (ParameterResult c : test.getParameterResults()) {
                    value = Utils.readLineFromConsole("Set an hipothethic value for " + c.getParameter().getDesignation());
                    test.setValues(value, c);
                }
                if (test == null)
                    exit = 0;
                System.out.println("-------- Test Results. --------");
                for (ParameterResult c : ctrl.getValues(test)) {
                    c.toString();
                }
                int confirmation;
                String report;
                do {

                    do {
                        report = Utils.readLineFromConsole("Write your diagnosis report:");
                        confirmation = Utils.readIntegerFromConsole(String.format("Are you sure you about your report? 1-YES 2-NO \n %s", report));
                    } while (confirmation != 1 || confirmation != 2);
                    if(confirmation == 1) {
                        if(ctrl.saveReport(test, report))
                            System.out.println("Report completed successfully.");
                        run();
                    }
                } while (confirmation != 1);
            } while (exit != 0);
        } catch (Exception e){
            System.out.println("There was an error when trying to write a test report.");
            return;
        }

    }
}
