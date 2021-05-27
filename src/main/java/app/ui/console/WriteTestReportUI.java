package app.ui.console;



import app.controller.WriteTestReportController;
import app.domain.model.ParameterResult;
import app.ui.console.utils.Utils;
import auth.mappers.dto.OrgRoleDto;
import auth.mappers.dto.TestDto;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLOutput;
import java.util.List;

public class WriteTestReportUI implements Runnable {
    private WriteTestReportController ctrl;
    public WriteTestReportUI() { ctrl = new WriteTestReportController(); }

    public void run(){
        int exit = 1;
        do {
            List<TestDto> listDto = ctrl.getAnalyzedTests();
            TestDto test = (TestDto) Utils.showAndSelectOne(listDto,"------ List of Tests available do to diagnosis report. ------" );
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
    }
}
