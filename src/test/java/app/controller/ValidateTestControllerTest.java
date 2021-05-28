package app.controller;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import auth.domain.store.TestStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ValidateTestControllerTest {

    @Test
    public void getAllTestsDto() {
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList);
        TestStore store = new TestStore();


        store.addTest(new app.domain.model.Test());
    }

    @Test
    public void validateReport() {
    }

    @Test
    public void validateTest() {
    }

    @Test
    public void sendNotification() {
    }
}