package auth.mappers;

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
}
