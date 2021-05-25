package auth.mappers;

import app.domain.model.Test;
import app.domain.model.TestType;
import auth.mappers.dto.TestDto;

import java.util.ArrayList;
import java.util.List;

public class TestMapper {

    public static List<TestDto> testMapperToDto (List<Test> listTest) {
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
}
