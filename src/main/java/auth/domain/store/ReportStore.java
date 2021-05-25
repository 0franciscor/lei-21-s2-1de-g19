package auth.domain.store;

import app.domain.model.Report;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ReportStore {
    private List<Report> reportList;

    public ReportStore() {

    }
    public boolean saveReport(String reportTxt, String testCode) {
        Report rep = new Report(reportTxt, testCode);
        if (validateReport(rep)) {
            reportList.add(rep);
            return true;
        }
        return false;
    }
    public boolean validateReport(Report rep) {
       for (Report c : reportList){
           if (c.getTestCode().equalsIgnoreCase(rep.getTestCode())) {
               return true;
           }
       }
       return false;
    }

}
