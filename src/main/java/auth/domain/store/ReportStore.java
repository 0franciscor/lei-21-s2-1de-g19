package auth.domain.store;

import app.domain.model.Report;
import app.domain.model.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Report Store.
 *
 * @author Alexandre Soares
 */
public class ReportStore implements Serializable {

    /**
     * A list of Reports
     */
    private List<Report> reportList;

    /**
     * The ReportStore builder.
     */
    public ReportStore() {
        reportList = new ArrayList<>();
    }

    /**
     * @param reportTxt A sentence which contains the Report.
     * @param testCode A code that identifies the Report.
     *
     * Method that is responsible for creating and saving a report.
     *
     * @return the success of the operation.
     */
    public boolean saveReport(String reportTxt, String testCode) {
        Report rep = new Report(reportTxt, testCode);
        if (validateReport(rep)) {
            reportList.add(rep);
            guardarFicheiroBinario(this);
            return true;
        }
        return false;
    }

    /**
     * @param rep the report that is intended to be validated.
     *
     * Method that is responsible for validating a report.
     *
     * @return the success of the operation.
     */
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

    /**
     * Method responsible for retrieving the reports list.
     *
     * @return a list of available reports.
     */
    public List<Report> getReportList(){
        return this.reportList;
    }
    public Report getReport(String code) {
        for (Report c : this.reportList) {
            if (c.getTestCode().equalsIgnoreCase(code))
                return c;
        }
        return null;
    }
    public boolean guardarFicheiroBinario(ReportStore store) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("ReportStore.bin"));
            try {
                out.writeObject(store);
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

}
