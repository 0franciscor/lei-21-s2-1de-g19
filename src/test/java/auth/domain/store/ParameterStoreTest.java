package auth.domain.store;

import app.controller.App;
import app.controller.CreateParameterCategoryController;
import app.controller.CreateParameterController;
import app.controller.RegisterTestController;
import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import auth.mappers.dto.ParameterCategoryDto;
import auth.mappers.dto.ParametersDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParameterStoreTest {

    @Test
    public void saveParameterInStore() {
        ParameterCategory parameterCategory=new ParameterCategory("a","bvgtr");
        Parameter parameter = new Parameter("abcde","abcdefghijklmnopqrst","abcdefgh",parameterCategory);
        ParameterStore parameterStore=new ParameterStore();

        boolean result = true;
        boolean expected =parameterStore.saveParameter(parameter);

        Assert.assertEquals(result, expected);
    }

    @Test
    public void doesNotSaveParameterInStore() {
        ParameterCategory parameterCategory=new ParameterCategory("a","bvgtr");
        Parameter parameter = new Parameter("abcde","abcdefghijklmnopqrst","abcdefgh",parameterCategory);
        ParameterStore parameterStore=new ParameterStore();

        boolean result=false;
        parameterStore.add(parameter);
        boolean expected=parameterStore.saveParameter(parameter);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void validateParameterExists() {
        ParameterCategory parameterCategory=new ParameterCategory("a","abcde");
        Parameter parameter=new Parameter("abcde","abcdefghijklmnopqrst","abcdefgh",parameterCategory);
        ParameterStore parameterStore= new ParameterStore();

        boolean result =false;
        parameterStore.add(parameter);
        boolean expected=parameterStore.validate(parameter);

        Assert.assertEquals(result, expected);
    }

    @Test
    public void validateParameterDoesNotExist() {
        ParameterCategory parameterCategory=new ParameterCategory("a","abcde");
        Parameter parameter=new Parameter("abcde","abcdefghijklmnopqrst","abcdefgh",parameterCategory);
        ParameterStore parameterStore=new ParameterStore();

        boolean result=true;
        boolean expected=parameterStore.validate(parameter);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void getAllParametersByParameterCategory(){

        App app = App.getInstance();
        ParameterStore parameterStore = new ParameterStore();
        ParameterCategoryStore parameterCategoryStore = app.getCompany().getParameterCategoryStore();

        List<ParameterCategoryDto> parameterCategoryDtoList = new ArrayList<>();
        ParameterCategoryDto parameterCategoryDto = new ParameterCategoryDto("cvfrt","78906");
        parameterCategoryDtoList.add(parameterCategoryDto);

        ParameterCategory parameterCategory = new ParameterCategory("cvfrt","78906");
        parameterCategoryStore.addParameterCategory(parameterCategory);

        Parameter parameter = new Parameter("12345","hjuio","bnhyu",parameterCategory);
        parameterStore.add(parameter);

        List<Parameter> parametersListResult = parameterStore.getAllParametersByParameterCategory(parameterCategoryDtoList);

        Parameter parameter1 = new Parameter("12345","hjuio","bnhyu",parameterCategory);

        List<Parameter> parametersListExpected = new ArrayList<>();
        parametersListExpected.add(parameter1);

        Assert.assertEquals(parametersListResult.toString(),parametersListExpected.toString());

    }
}