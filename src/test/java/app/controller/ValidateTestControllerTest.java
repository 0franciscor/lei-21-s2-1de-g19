package app.controller;

import app.domain.model.*;
import app.domain.shared.ExternalModuleBloodWithoutKey;
import auth.domain.store.ReportStore;
import auth.domain.store.TestStore;
import auth.mappers.dto.TestDto;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidateTestControllerTest {

    @Test
    public void getAllTestsDto(){
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        List<Parameter> pList = new ArrayList<>();
        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
        pList.add(parameter1);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());


        app.domain.model.Test test = new app.domain.model.Test(testType, pList, pcList, "1123456789", "123456781234");
        test.setChemicalAnalysisDateTime(new Date());
        test.setDiagnosisDateTime(new Date());
        test.generateCode();

        TestStore testStore = App.getInstance().getCompany().getTestStore();
        ValidateTestController vtController = new ValidateTestController();

        test.setStatus(app.domain.model.Test.Status.Reported);
        testStore.saveTest(test);

        List<TestDto> listFromController = vtController.getAllTestsDto();

        List<TestDto> listExpected = new ArrayList<>();
        TestDto testDto = new TestDto(test.getRegistrationDateTime(), test.getChemicalAnalysisDateTime(), test.getDiagnosisDateTime(), test.getCode());
        listExpected.add(testDto);

        assertEquals(listFromController, listExpected);
    }

    @Test
    public void validateReport() {
        ReportStore reportStore = App.getInstance().getCompany().getReportStore();
        reportStore.saveReport("Report", "123456781234");
        reportStore.validateReport("123456781234");
        assertTrue(reportStore.getReportList().get(0).getValidation());
    }

    @Test
    public void validateTest() {
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        List<Parameter> pList = new ArrayList<>();
        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
        pList.add(parameter1);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());


        app.domain.model.Test test = new app.domain.model.Test(testType, pList, pcList, "1123456780", "123456781233");
        test.setChemicalAnalysisDateTime(new Date());
        test.setDiagnosisDateTime(new Date());
        test.setStatus(app.domain.model.Test.Status.Reported);
        test.generateCode();


        TestStore testStore = App.getInstance().getCompany().getTestStore();
        testStore.saveTest(test);

        ValidateTestController vtController = new ValidateTestController();

        ReportStore reportStore = App.getInstance().getCompany().getReportStore();
        reportStore.saveReport("Report", test.getCode());

        vtController.validateReport(test.getCode());
        vtController.validateTest(test.getCode());
        test.updateValidationDateTime();

        assertEquals(app.domain.model.Test.Status.Validated.toString(),test.getStatus());

    }

    @Test
    public void notificationService() throws IOException {
        boolean isNull = App.getInstance().getCompany().getNotificationService() != null;
        assertTrue(isNull);
    }
}