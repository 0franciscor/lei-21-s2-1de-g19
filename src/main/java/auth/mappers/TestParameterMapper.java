package auth.mappers;

import app.domain.model.*;

import auth.mappers.dto.TestParameterDto;

import java.util.ArrayList;
import java.util.List;

public class TestParameterMapper {

    /**
     * Responsible for converting a list of test parameters into a list of test parameters DTO.
     *
     * @param testParameterList that needs to be converted to a Dto.
     * @return a List of Dto Test Parameters
     */
    public static List<TestParameterDto> toDto(List<TestParameter> testParameterList) {
        List<TestParameterDto> testParameterListDto = new ArrayList<>();
        for(TestParameter testParameter: testParameterList) {
            testParameterListDto.add(new TestParameterDto(testParameter.getParameter(), testParameter.getTestParameterResult()));
        }
        return testParameterListDto;
    }

    /**
     * Responsible for converting a list of test parameters Dto into a list of test parameters.
     *
     * @param testParameterListDto a list of test parameters Dto
     * @return a list of test parameters
     */
    public static List<TestParameter> toModel (List<TestParameterDto> testParameterListDto){

        List<TestParameter> testParameters = new ArrayList<>();

        for (TestParameterDto testParameterDto: testParameterListDto ) {
            testParameters.add(new TestParameter(testParameterDto.getParameter(), testParameterDto.getTestParameterResult()));
        }
        return testParameters;

    }

}
