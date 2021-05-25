package app.ui.console;



import app.controller.WriteTestReportController;
import app.ui.console.utils.Utils;
import auth.mappers.dto.OrgRoleDto;
import auth.mappers.dto.TestDto;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class WriteTestReportUI implements Runnable {
    private WriteTestReportController ctrl;
    public WriteTestReportUI() { ctrl = new WriteTestReportController(); }

    public void run(){
        int confirmation = 0;
        do {
            List<TestDto> listDto = ctrl.getAnalyzedTests();
            Object test = Utils.showAndSelectOne(listDto,"------ List of Tests available do to diagnosis report. ------" );

        } while (confirmation != 0);
    }
}
