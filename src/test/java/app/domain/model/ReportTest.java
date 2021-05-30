package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReportTest {

    @Test
    public void checkReportRulesValid() {
        Report report = new Report("Report", "00000000001");
        String test = "Diagnosis Report";
        report.checkReportRules(test);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkReportRulesisBlank() {
        Report report = new Report("Report", "00000000001");
        String test = "";
        report.checkReportRules(test);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkReportRulesisOutOfLenght() {
        Report report = new Report("Report", "00000000001");
        String test = "ABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJ0";
        report.checkReportRules(test);
    }

    @Test
    public void getTestCode() {
        Report report = new Report("Report", "00000000001");
        String result = report.getTestCode();
        String expected = "00000000001";
        Assert.assertEquals(expected, result);

    }

    @Test
    public void getValidation() {
        Report report = new Report("Report", "00000000001");
        boolean result = report.getValidation();
        boolean expected = false;
        Assert.assertEquals(result, expected);
    }

    @Test
    public void setValidationTrue() {
        Report report = new Report("Report", "00000000001");
        report.setValidation(true);
        assertTrue(report.getValidation());
    }

    @Test
    public void setValidationFalse(){
        Report report = new Report("Report", "00000000001");
        assertFalse(report.getValidation());
    }
}