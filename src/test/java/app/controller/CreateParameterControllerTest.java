package app.controller;

import app.controller.CreateParameterController;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CreateParameterControllerTest {

    @Test
    public void saveParameter() {
        CreateParameterController cpc=new CreateParameterController();
        boolean result=cpc.saveParameter();
        assertFalse(result);
    }

}