package auth.mappers;

import app.domain.model.ParameterCategory;
import auth.mappers.dto.ParameterCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class ParameterCategoryMapper {

    public static List<ParameterCategoryDto> toDto (List<ParameterCategory> parameterCategory){
        List<ParameterCategoryDto> parameterCategoryDto= new ArrayList<>();
        for(ParameterCategory parameterCategoryEach : parameterCategory)
            parameterCategoryDto.add(new ParameterCategoryDto(parameterCategoryEach.getName(), parameterCategoryEach.getCode()));

        return parameterCategoryDto;
    }
}