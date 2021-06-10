package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.shared.ExternalModuleBloodWithoutKey;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * RegisterClinicalAnalysisLaboratoryTest class, which is responsible for
 * testing the RegisterClinicalAnalysisLaboratoryController.
 *
 * @author Nuno Pinho(1190918)
 */
public class RegisterClinicalAnalysisLaboratoryControllerTest {

    @Test
    public void RegisterClinicalAnalysisLaboratory() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", pcList, new ExternalModuleBloodWithoutKey()));
        RegisterClinicalAnalysisLaboratoryController calControllerTest = new RegisterClinicalAnalysisLaboratoryController();
        boolean calTest = calControllerTest.registerClinicalAnalysisLaboratory("Lab", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
        assertEquals(true, calTest);
    }

    @Test
    public void saveClinicalAnalysisLaboratory() {
        RegisterClinicalAnalysisLaboratoryController calControllerTest = new RegisterClinicalAnalysisLaboratoryController();
        boolean calTest = calControllerTest.saveClinicalAnalysisLaboratory();
        assertEquals(false, calTest);
    }

    @Test
    public void getAllTestTypes() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", pcList, new ExternalModuleBloodWithoutKey()));
        RegisterClinicalAnalysisLaboratoryController calControllerTest = new RegisterClinicalAnalysisLaboratoryController();
        ClinicalAnalysisLaboratory cal1 = new ClinicalAnalysisLaboratory("Lab1", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
        ClinicalAnalysisLaboratory cal2 = new ClinicalAnalysisLaboratory("Lab2", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);

        calControllerTest.registerClinicalAnalysisLaboratory("Lab1", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
        calControllerTest.saveClinicalAnalysisLaboratory();
        calControllerTest.registerClinicalAnalysisLaboratory("Lab2", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
        calControllerTest.saveClinicalAnalysisLaboratory();

        List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratoriesExpected = new ArrayList<>();

        clinicalAnalysisLaboratoriesExpected.add(cal1);
        clinicalAnalysisLaboratoriesExpected.add(cal2);

        List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratoriesResult = calControllerTest.getAllClinicalAnalysisLaboratories();

        Assert.assertEquals(clinicalAnalysisLaboratoriesExpected.toString(), clinicalAnalysisLaboratoriesResult.toString());

    }

}
