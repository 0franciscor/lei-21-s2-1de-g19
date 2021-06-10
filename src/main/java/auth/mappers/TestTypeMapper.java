package auth.mappers;

import app.domain.shared.ExternalModule;
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
            testTypeDto.add(new TestTypeDto(testTypeEach.getCode(), testTypeEach.getDescription(), testTypeEach.getCollectingMethod(), testTypeEach.getParameterCategoriesList(), testTypeEach.getExternalModule()));

        return testTypeDto;
    }

    /**
     * Builds a test type instance, receiving the testTypeDto.
     *
     * @param testTypeDto a testTypeDto
     * @return a testType which contains the converted objects
     */
    public static TestType toModel (TestTypeDto testTypeDto) {

        String code = testTypeDto.getCode();
        String description = testTypeDto.getDescription();
        String collectingMethod = testTypeDto.getCollectingMethod();
        List<ParameterCategory> parameterCategoryList = testTypeDto.getParameterCategoriesList();
        ExternalModule externalModule = testTypeDto.getExternalModule();

        TestType testType = new TestType(code, description, collectingMethod, parameterCategoryList, externalModule);
        return testType;

    }
}
