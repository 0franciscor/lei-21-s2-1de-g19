package app.controller;

import app.domain.model.Test;
import auth.domain.store.TestStore;
import auth.mappers.TestMapper;
import auth.mappers.dto.TestDto;

import java.util.ArrayList;
import java.util.List;

public class ViewTestsController {

    public ViewTestsController() {
    }

    public List<TestDto> getTests() {
        TestStore store = App.getInstance().getCompany().getTestStore();
        List<Test> testList = store.getDiagnosedTests();
        List<TestDto> testDtoList = TestMapper.toDtoClient(testList);
        return testDtoList;
    }

    public List<TestDto> getClientTests(String TIN) {
        List<TestDto> testDtoList = getTests();
        List<TestDto> specificTests = getSpecificTests(TIN, testDtoList);
        return specificTests;
    }

    public List<TestDto> getSpecificTests(String TIN, List<TestDto> testDtoList) {
        List<TestDto> testDtoListSpecific = new ArrayList<>();
        for (TestDto testDto : testDtoList) {
            if (testDto.getTIN().equalsIgnoreCase(TIN)) {
                testDtoListSpecific.add(testDto);
            }
        }
        return testDtoListSpecific;
    }

}

