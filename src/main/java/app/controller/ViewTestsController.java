package app.controller;

import app.domain.model.Test;
import auth.domain.store.TestStore;
import auth.mappers.TestMapper;
import auth.mappers.dto.TestDto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco Redol
 */
public class ViewTestsController {

    /**
     * The ViewTestsController Builder
     */
    public ViewTestsController() {}

    /**
     * Method responsible for retrieving a list of validated tests.
     *
     * @return a list of Validated Tests from the Test Store.
     */
    public List<TestDto> getTests() {
        TestStore store = App.getInstance().getCompany().getTestStore();
        List<Test> testList = store.getDiagnosedTests();
        return TestMapper.toDtoClient(testList);
    }

    /**
     * @param TIN of the client
     *
     * Method responsible for returning a list of Client Specific tests
     *
     * @return a list of Specific tests, to the UI
     */
    public List<TestDto> getClientTests(String TIN) {
        List<TestDto> testDtoList = getTests();
        return getSpecificTests(TIN, testDtoList);
    }

    /**
     * @param TIN of the client.
     * @param testDtoList list from the Store
     *
     * Method responsible for searching from a list of tests, tests with a Specific TIN.
     *
     * @return a list with the test/s that belong to the client.
     */
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

