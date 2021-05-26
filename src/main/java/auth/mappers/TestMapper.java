package auth.mappers;

import app.domain.model.Sample;
import app.domain.model.Test;
import auth.mappers.dto.TestDto;
import java.util.ArrayList;
import java.util.List;

public class TestMapper {

    public static List<TestDto> toDto (List<Test> listTest) {
        List<TestDto> listAnalyzedTestsDto = new ArrayList<TestDto>();
        for(Test c: listTest) {
            String description = c.getDescription();
            String testType = c.getTestType().getDescription();
            String code = c.getCode();
            TestDto testDto = new TestDto(description, testType, code);
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

    public static List<TestDto> ModelToDto (List<Test> listTest){
        List<TestDto> listTestsDto = new ArrayList<>();
        for(Test t: listTest){
            String code = t.getCode();
            TestDto testDto = new TestDto(code);
            listTestsDto.add(testDto);
        }
        return listTestsDto;
    }

    public static TestDto ModelToDto (Test test){
        ArrayList<Sample> listSamplesTest = test.getListSamples();
        TestDto testDto = new TestDto(listSamplesTest);

        return testDto;
    }

    public static String DtoToModel (TestDto testDto){
        String code = testDto.getCode();
        return code;
    }
}
