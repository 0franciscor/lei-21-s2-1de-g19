package auth.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.shared.ExternalModuleBloodWithoutKey;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

/**
 * ClinicalAnalysisLaboratoryStoreTest class, which is responsible for testing
 * the ClinicalAnalysisLaboratoryStore.
 *
 * @author Nuno Pinho (1190918)
 */
public class ClinicalAnalysisLaboratoryStoreTest {

    @Test
    public void registerClinicalAnalysisLaboratory() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", pcList, new ExternalModuleBloodWithoutKey()));
        ClinicalAnalysisLaboratoryStore calStoreTest = new ClinicalAnalysisLaboratoryStore();
        ClinicalAnalysisLaboratory cal1 = calStoreTest.registerClinicalAnalysisLaboratory("Lab", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
        assertEquals(cal1.toString(), calStoreTest.registerClinicalAnalysisLaboratory("Lab", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1).toString());
    }

    @Test
    public void saveClinicalAnalysisLaboratory() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", pcList, new ExternalModuleBloodWithoutKey()));
        ClinicalAnalysisLaboratoryStore calStoreTest = new ClinicalAnalysisLaboratoryStore();
        ClinicalAnalysisLaboratory cal1 = calStoreTest.registerClinicalAnalysisLaboratory("Lab", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
        assertTrue(calStoreTest.saveClinicalAnalysisLaboratory(cal1));
    }

    @Test
    public void validateClinicalAnalysisLaboratory() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", pcList, new ExternalModuleBloodWithoutKey()));
        ClinicalAnalysisLaboratoryStore calStoreTest = new ClinicalAnalysisLaboratoryStore();
        ClinicalAnalysisLaboratory cal1 = calStoreTest.registerClinicalAnalysisLaboratory("Lab", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
        assertTrue(calStoreTest.validateClinicalAnalysisLaboratory(cal1));
    }

    @Test
    public void addClinicalAnalysisLaboratory() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", pcList, new ExternalModuleBloodWithoutKey()));
        ClinicalAnalysisLaboratoryStore calStoreTest = new ClinicalAnalysisLaboratoryStore();
        ClinicalAnalysisLaboratory cal1 = calStoreTest.registerClinicalAnalysisLaboratory("Lab", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
        assertTrue(calStoreTest.addClinicalAnalysisLaboratory(cal1));
    }
}
