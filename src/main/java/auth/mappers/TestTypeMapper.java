package auth.mappers;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import auth.mappers.dto.TestTypeDto;
import java.util.ArrayList;
import java.util.List;

/**
 * TestTypeMapper class, which is responsible for converting a TestTypeList to TestTypeDtoList.
 *
 * @author Francisco Redol (1201239)
 */
public class TestTypeMapper {

    /**
     * @param testType a TestType List
     *
     * Responsible for converting a TestTypeList containing TestType Objects to TestTypeDto Objects
     *
     * @return a testTypeDto List which contains the converted objects
     */
    public static List<TestTypeDto> toDto (List<TestType> testType){
        List<TestTypeDto> testTypeDto= new ArrayList<>();

        for(TestType testTypeEach : testType)
            if(testTypeEach.getParameterCategoriesList() == null)
                testTypeDto.add(new TestTypeDto(testTypeEach.getCode(), testTypeEach.getDescription(), testTypeEach.getCollectingMethod(), testTypeEach.getParameterCategory()));
            else
                testTypeDto.add(new TestTypeDto(testTypeEach.getCode(), testTypeEach.getDescription(), testTypeEach.getCollectingMethod(), testTypeEach.getParameterCategoriesList()));

        return testTypeDto;
    }

    public static TestType toModel (TestTypeDto testTypeDto) {

        String code = testTypeDto.getCode();
        String description = testTypeDto.getDescription();
        String collectingMethod = testTypeDto.getCollectingMethod();
        ParameterCategory parameterCategory = testTypeDto.getParameterCategory();
        List<ParameterCategory> parameterCategoryList = testTypeDto.getParameterCategoriesList();

        if (testTypeDto.getParameterCategoriesList() == null){
            TestType testType = new TestType(code, description, collectingMethod, parameterCategory);
            return testType;
        } else {
            TestType testType1 = new TestType(code, description, collectingMethod, parameterCategoryList);
            return testType1;
        }
    }
}
