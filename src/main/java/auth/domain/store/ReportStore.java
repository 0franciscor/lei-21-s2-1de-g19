package auth.domain.store;

import app.domain.model.Report;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReportStore {
    private List<Report> reportList;

    public ReportStore() {
        reportList = new ArrayList<>();
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
               return false;
           }
       }
       return true;
    }

    /**
     * @param code that identifies a Report object.
     *
     * This method is able to validate an existent report through its code.
     *
     * @return the success of the operation (true if successful and false if not).
     */
    public boolean validateReport(String code) {
        for (Report report : reportList){
            if (report.getTestCode().equalsIgnoreCase(code)){
                report.setValidation(true);
                return true;
            }
        }
        return false;
    }

    /**
     * @param code that identifies a Report object.
     *
     * This method is able to retrieve the validation state of a given Report through its code.
     *
     * @return the Report validation state (and if it's not found, false).
     */
    public boolean getReportValidation(String code){
        for(Report report : reportList){
            if(report.getTestCode().equalsIgnoreCase(code))
                return report.getValidation();
        }
        return false;
    }

    public List<Report> getReportList(){
        return this.reportList;
    }

}
