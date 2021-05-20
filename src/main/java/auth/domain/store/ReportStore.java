package auth.domain.store;

import app.domain.model.Report;

import java.util.List;

public class ReportStore {
    private List<Report> reportList;

    public ReportStore() {

    }
    public boolean addReport (Report report) {
        if (reportList.contains(report)){
            reportList.add(report);
            return true;
        }
        return false;
    }
}
