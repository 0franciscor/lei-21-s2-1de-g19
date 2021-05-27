package auth.mappers;

import app.domain.model.*;

import auth.mappers.dto.TestParameterDto;

import java.util.ArrayList;
import java.util.List;

public class TestParameterMapper {

    /**
     * Responsible for converting a list of test parameters into a list of test parameters DTO.
     *
     * @param parameterResultList that needs to be converted to a Dto.
     * @return a List of Dto Test Parameters
     */
    public static List<TestParameterDto> toDto(List<ParameterResult> parameterResultList) {
        List<TestParameterDto> testParameterListDto = new ArrayList<>();
        for(ParameterResult parameterResult : parameterResultList) {
            testParameterListDto.add(new TestParameterDto(parameterResult.getParameter(), parameterResult.getTestParameterResult()));
        }
        return testParameterListDto;
    }

    /**
     * Responsible for converting a list of test parameters Dto into a list of test parameters.
     *
     * @param testParameterListDto a list of test parameters Dto
     * @return a list of test parameters
     */
    public static List<ParameterResult> toModel (List<TestParameterDto> testParameterListDto){

        List<ParameterResult> parameterResults = new ArrayList<>();

        for (TestParameterDto testParameterDto: testParameterListDto ) {
            parameterResults.add(new ParameterResult(testParameterDto.getParameter(), testParameterDto.getTestParameterResult()));
        }
        return parameterResults;

    }

}
