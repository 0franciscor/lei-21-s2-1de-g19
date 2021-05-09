import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import auth.domain.store.ParameterStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

}