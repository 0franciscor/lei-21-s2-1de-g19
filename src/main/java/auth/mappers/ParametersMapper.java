package auth.mappers;

import app.domain.model.Parameter;
import auth.mappers.dto.ParametersDto;

import java.util.ArrayList;
import java.util.List;

public class ParametersMapper {

    public static List<ParametersDto> toDto (List<Parameter> parameters){

        List<ParametersDto> parametersDtoList = new ArrayList<>();

        for (Parameter parameter: parameters ) {

            parametersDtoList.add(new ParametersDto(parameter.getDesignation(), parameter.getDescription(), parameter.getCode(), parameter.getPcat()));
        }

        return parametersDtoList;
    }

    public static List<Parameter> toModel (List<ParametersDto> parametersDtoList){

        List<Parameter> parameters = new ArrayList<>();

        for (ParametersDto parametersDto: parametersDtoList ) {
            parameters.add(new Parameter(parametersDto.getDesignation(), parametersDto.getDescription(), parametersDto.getCode(), parametersDto.getParameterCategory()));
        }
        return parameters;

    }
}
