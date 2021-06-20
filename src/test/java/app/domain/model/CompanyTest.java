package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class CompanyTest {

    @Test
    public void generateNHSReportLinear() {
        Company company = new Company("Many Labs");

        NHSReport nhsReportExpected = new NHSReport(95,95,true);
        NHSReport nhsReportResult = company.generateNHSReport(95,95,true);

        Assert.assertEquals(nhsReportExpected.toString(), nhsReportResult.toString());

    }


    @Test
    public void GenerateNHSReportMultilinear() {

        Company company = new Company("Many Labs");

        NHSReport nhsReportExpected = new NHSReport(95,95);
        NHSReport nhsReportResult = company.generateNHSReport(95,95);

        Assert.assertEquals(nhsReportExpected.toString(), nhsReportResult.toString());
    }
}