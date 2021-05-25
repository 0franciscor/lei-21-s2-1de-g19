package auth.mappers;

import app.domain.model.Test;
import auth.mappers.dto.TestDto;
import java.util.ArrayList;
import java.util.Date;
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

    public static List<TestDto> toDtoLabC (List<Test> testsList) {
        List<TestDto> testsListDto = new ArrayList<>();
        for(Test test: testsList) {
            Date registrationDateTime = test.getRegistrationDateTime();
            Date chemicalAnalysisDateTime = test.getChemicalAnalysisDateTime();
            Date diagnosisDateTime = test.getDiagnosisDateTime();
            String code = test.getCode();
            if(registrationDateTime != null && chemicalAnalysisDateTime != null && diagnosisDateTime != null && code != null){
                TestDto testDto = new TestDto(registrationDateTime, chemicalAnalysisDateTime, diagnosisDateTime, code);
                testsListDto.add(testDto);
            }
        }
        return testsListDto;
    }
}
