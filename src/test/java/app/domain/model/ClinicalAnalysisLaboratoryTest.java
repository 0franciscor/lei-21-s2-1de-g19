package app.domain.model;

import app.domain.shared.ExternalModuleBloodWithoutKey;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicalAnalysisLaboratoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void setName() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAddress() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setAddress("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPhoneNumber() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setPhoneNumber("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTIN() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setTIN("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLabID() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setLabID("");
    }

    @Test(expected = NullPointerException.class)
    public void setTestTypesList() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setTestTypesList(new ArrayList<>());
    }

    @Test
    public void setNameCorrect() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setName("joao");
    }

    @Test
    public void setAddressCorrect() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setAddress("Rua da Palmeira");
    }

    @Test
    public void setPhoneNumberCorrect() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setPhoneNumber("93393393312");
    }

    @Test
    public void setTINCorrect() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setTIN("1234567890");
    }

    @Test
    public void setLabIDCorrect() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setLabID("12345");
    }

    @Test
    public void setTestTypesListCorrect() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        clinicalAnalysisLaboratory.setTestTypesList(TestTypeList);
    }

    @Test
    public void testToString() {
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        ParameterCategory parameterCategory = new ParameterCategory("covid", "c0vid");
        parameterCategoryList.add(parameterCategory);

        List<TestType> TestTypeList = new ArrayList<>();
        TestTypeList.add(new TestType("c0vid", "isCovid", "Swab", parameterCategoryList, new ExternalModuleBloodWithoutKey()));

        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory("Lab", "Rua da Palmeira", "93393393312", "1234567890", "1234", TestTypeList);

        assertEquals("The Laboratory's name is Lab, the address is Rua da Palmeira, the phone number is 93393393312, the TIN is 1234567890 and the ID is 1234. [Test type with code c0vid, analyses isCovid, and it's collecting method is Swab. [Parameter Category name is covid and it's code is c0vid] External Module Blood (without key).]", clinicalAnalysisLaboratory.toString());
    }
}