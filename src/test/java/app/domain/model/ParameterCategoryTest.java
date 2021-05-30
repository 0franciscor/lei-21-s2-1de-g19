package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ParameterCategoryTest class, which is responsible for testing the ParameterCategory.
 *
 * @author Francisco Redol (1201239)
 */
public class ParameterCategoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void setNameEmpty() {
        ParameterCategory test = new ParameterCategory("", "c0de1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNameBeyondLimit() {
        ParameterCategory test = new ParameterCategory("12345678912345678343524234234234234", "c0de1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCodeEmpty() {
        ParameterCategory test2 = new ParameterCategory("covid", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCodeBeyondLimit() {
        ParameterCategory test2 = new ParameterCategory("covid", "22222222222222222222222");
    }

    @Test
    public void setNameCorrect() {
        ParameterCategory test = new ParameterCategory("covid", "c0de1");
    }

    @Test
    public void setCodeCorrect() {
        ParameterCategory test2 = new ParameterCategory("covid", "c0vid");
    }

    @Test
    public void getName(){
        ParameterCategory test2 = new ParameterCategory("covid", "c0vid");
        assertEquals("covid", test2.getName());
    }

    @Test
    public void getCode(){
        ParameterCategory test2 = new ParameterCategory("covid", "c0vid");
        assertEquals("c0vid", test2.getCode());
    }
}