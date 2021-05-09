package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ParameterCategoryTest class, which is responsible for testing the ParameterCategory.
 *
 * @author Francisco Redol (1201239)
 */
public class ParameterCategoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void setName() {
        ParameterCategory test = new ParameterCategory("", "c0de1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCode() {
        ParameterCategory test2 = new ParameterCategory("covid", "");
    }
}