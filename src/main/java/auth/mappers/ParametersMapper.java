package auth.mappers;

import app.domain.model.Parameter;
import auth.mappers.dto.ParametersDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Parameters Mapper.
 *
 * @author Eduardo Gonçalves
 */
public class ParametersMapper {


    /**
     * Responsible for converting a list of parameters into a list of parameters Dto.
     *
     * @param parameters a parameters list
     * @return a list of parameters Dto
     */
    public static List<ParametersDto> toDto (List<Parameter> parameters){

        List<ParametersDto> parametersDtoList = new ArrayList<>();

        for (Parameter parameter: parameters ) {

            parametersDtoList.add(new ParametersDto(parameter.getDesignation(), parameter.getDescription(), parameter.getCode(), parameter.getPcat()));
        }

        return parametersDtoList;
    }

    /**
     * Responsible for converting a list of parameters Dto into a list of parameters.
     *
     * @param parametersDtoList a list of parameters Dto
     * @return a list of parameters
     */
    public static List<Parameter> toModel (List<ParametersDto> parametersDtoList){

        List<Parameter> parameters = new ArrayList<>();

        for (ParametersDto parametersDto: parametersDtoList ) {
            parameters.add(new Parameter(parametersDto.getCode(), parametersDto.getDescription(), parametersDto.getDesignation() , parametersDto.getParameterCategory()));
        }
        return parameters;

    }
}
