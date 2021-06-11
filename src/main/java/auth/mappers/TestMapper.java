package auth.mappers;

import app.domain.model.*;
import auth.mappers.dto.TestDto;

import java.util.ArrayList;
import java.util.List;

public class TestMapper {

    public static List<TestDto> toDto (List<Test> listTest) {
        List<TestDto> listAnalyzedTestsDto = new ArrayList<TestDto>();
        for(Test c: listTest) {
            String description = c.getDescription();
            TestType testType = c.getTestType();
            String code = c.getCode();
            List<TestParameter> parameterList = c.getTestParameterList();
            TestDto testDto = new TestDto(description, testType, code, parameterList);
            listAnalyzedTestsDto.add(testDto);
        }
        return listAnalyzedTestsDto;
    }

    /**
     * @param testsList that needs to be converted to a Dto.
     *
     * Responsible for converting a list of tests into a DTO list of tests.
     *
     * @return a List of Dto Tests
     */
    public static List<TestDto> toDtoLabC (List<Test> testsList) {
        List<TestDto> testsListDto = new ArrayList<>();
        for(Test test: testsList) {
            testsListDto.add(new TestDto(test.getRegistrationDateTime(), test.getChemicalAnalysisDateTime(),
                    test.getDiagnosisDateTime(), test.getCode()));
        }
        return testsListDto;
    }

    /**
     * Responsible for converting a list of tests into a DTO list of tests.
     *
     * @param listTest
     * @return a list of dto tests
     */
    public static List<TestDto> ModelToDto (List<Test> listTest){
        List<TestDto> listTestsDto = new ArrayList<>();
        for(Test t: listTest){
            Client client=t.getClient();
            String name=client.getName();
            TestType testType = t.getTestType();
            List<ParameterCategory> parameterCategories = t.getParameterCategories();
            List<Parameter> parameters = t.getParameters();
            String code=t.getCode();
            TestDto testDto = new TestDto(name, testType, parameterCategories, parameters, code);
            listTestsDto.add(testDto);
        }
        return listTestsDto;
    }

    /**
     * Responsible for converting a test into a DTO test.
     *
     * @param test
     * @return a test in data transfer object
     */
    public static TestDto ModelToDto (Test test){
        ArrayList<Sample> listSamplesTest = test.getListSamples();
        TestDto testDto = new TestDto(listSamplesTest);

        return testDto;
    }

    /**
     * Responsible for converting a DTO test, who only has a code, into a test code
     *
     * @param testDto
     * @return a test code
     */
    public static String DtoToModel (TestDto testDto){
        String code = testDto.getCode();
        return code;
    }

    public static List<TestDto> toDtoClient(List<Test> testList){
        List<TestDto> testListDto = new ArrayList<>();
        for(Test test: testList) {
            testListDto.add(new TestDto(test.getTestType(), test.getChemicalAnalysisDateTime(),
                    test.getParameterResults(), test.getCode(), test.getClient().getTIN()));
        }
        return testListDto;
    }
    public static List<TestDto> toDTOTestMapper (List<Test> testList){
        List<TestDto> testListDto = new ArrayList<>();
        for(Test test: testList) {
            testListDto.add(new TestDto(test.getTestType(), test.getChemicalAnalysisDateTime(),
                    test.getParameterResults(), test.getCode(), test.getClient().getTIN(), test.getReport()));
        }
        return testListDto;
    }

    public static TestDto toDtoCCT(Test test){
        return new TestDto(test.getParameters());
    }

}
