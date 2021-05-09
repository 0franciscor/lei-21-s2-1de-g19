package app.domain.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * ClinicalAnalysisLaboratoryTest class, which is responsible for testing the
 * ClinicalAnalysisLaboratory.
 *
 * @author Nuno Pinho (1190918)
 */
public class ClinicalAnalysisLaboratoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void setName() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", new ParameterCategory("null", "pcnul")));
        ClinicalAnalysisLaboratory test = new ClinicalAnalysisLaboratory("", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAddress() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", new ParameterCategory("null", "pcnul")));
        ClinicalAnalysisLaboratory test2 = new ClinicalAnalysisLaboratory("", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPhoneNumber() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", new ParameterCategory("null", "pcnul")));
        ClinicalAnalysisLaboratory test3 = new ClinicalAnalysisLaboratory("", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTIN() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", new ParameterCategory("null", "pcnul")));
        ClinicalAnalysisLaboratory test4 = new ClinicalAnalysisLaboratory("", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLabID() {
        List<TestType> TestTypesList1 = new ArrayList<>();
        TestTypesList1.add(new TestType("12345", "Description", "Collect1", new ParameterCategory("null", "pcnul")));
        ClinicalAnalysisLaboratory test5 = new ClinicalAnalysisLaboratory("", "Avenida da Boavista", "931234567", "12345", "123456789", TestTypesList1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTestTypesList() {
        List<TestType> testList = new ArrayList<>();
        testList.add(null);
        ClinicalAnalysisLaboratory test6 = new ClinicalAnalysisLaboratory("Lab", "Avenida da Boavista", "931234567", "12345", "123456789", testList);
    }

}
