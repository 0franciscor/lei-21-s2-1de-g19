package app.controller;

import app.domain.model.Report;
import auth.mappers.dto.TestDto;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WriteTestReportControllerTest {

    @Test
    public void saveReport() {
        WriteTestReportController ctrl = new WriteTestReportController();
        TestDto testDto = new TestDto("12345");
        String report = "Report";
        boolean expected = true;
        boolean result = ctrl.saveReport(testDto, report);
        Assert.assertEquals(expected, result);
    }
}