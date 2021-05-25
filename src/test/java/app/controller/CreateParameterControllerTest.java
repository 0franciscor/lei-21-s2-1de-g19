package app.controller;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreateParameterControllerTest {

    @Test
    public void saveParameter() {
        CreateParameterController cpc=new CreateParameterController();
        boolean result=cpc.saveParameter();
        assertFalse(result);
    }

}