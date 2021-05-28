package auth.domain.store;

import app.domain.model.Employee;
import app.domain.model.Report;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReportStoreTest {

    @Test
    public void saveReport() {
        ReportStore rps = new ReportStore();
        boolean expected = true;
        boolean result = rps.saveReport("Report", "00000000001");
        Assert.assertEquals(expected, result);
    }
    @Test
    public void doesNotSaveReport() {
        ReportStore rps = new ReportStore();
        boolean expected = false;
        rps.getReportList().add(new Report("Report", "00000000001"));
        boolean result = rps.saveReport("Report", "00000000001");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void validateReportExists() {
        ReportStore rps = new ReportStore();
        boolean expected = false;
        Report rep = new Report("Report", "00000000001");
        rps.getReportList().add(rep);
        boolean result = rps.validateReport(rep);
        assertEquals(expected, result);
    }
    @Test
    public void validateReportDoesNotExists() {
        ReportStore rps = new ReportStore();
        boolean expected = true;
        Report rep = new Report("Report", "00000000001");
        boolean result = rps.validateReport(rep);
        assertEquals(expected, result);
    }
}