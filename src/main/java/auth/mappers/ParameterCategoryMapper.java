package auth.mappers;

import app.domain.model.ParameterCategory;
import auth.mappers.dto.ParameterCategoryDto;

import java.util.ArrayList;
import java.util.List;

/**
 * ParameterCategoryMapper class, which is responsible for converting a ParameterCategoryList to ParameterCategoryDtoList.
 *
 * @author Francisco Redol (1201239)
 */
public class ParameterCategoryMapper {

    /**
     * @param parameterCategory a ParameterCategory List
     *
     * Responsible for converting a ParameterCategoryList containing ParameterCategory Objects to ParameterCategoryDto Objects
     *
     * @return a parameterCategoryDto List which contains the converted objects
     */
    public static List<ParameterCategoryDto> toDto (List<ParameterCategory> parameterCategory){
        List<ParameterCategoryDto> parameterCategoryDto= new ArrayList<>();

        for(ParameterCategory parameterCategoryEach : parameterCategory)
            parameterCategoryDto.add(new ParameterCategoryDto(parameterCategoryEach.getName(), parameterCategoryEach.getCode()));

        return parameterCategoryDto;
    }


    /**
     * Responsible for converting a list of parameter categories Dto into a list of parameter categories.
     *
     * @param parameterCategoryDtoList a list of parameter categories Dto
     * @return a list of parameter categories
     */
    public static List<ParameterCategory> toModel (List<ParameterCategoryDto> parameterCategoryDtoList){

        List<ParameterCategory> parameterCategories = new ArrayList<>();

            for (ParameterCategoryDto parameterCategoryDto: parameterCategoryDtoList ) {
            parameterCategories.add(new ParameterCategory(parameterCategoryDto.getName(), parameterCategoryDto.getCode()));
        }
        return parameterCategories;

    }
}