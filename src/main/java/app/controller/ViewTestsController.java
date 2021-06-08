package app.controller;

import app.domain.model.Test;
import auth.domain.store.TestStore;
import auth.mappers.TestMapper;
import auth.mappers.dto.TestDto;

import java.util.*;

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
        List<Test> testList = store.getValidatedTests();
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
        testDtoList = getSpecificTests(TIN, testDtoList);
        sortTestList(testDtoList);
        return testDtoList;
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

    public void sortTestList(List<TestDto> testDtoList){
        Collections.sort(testDtoList, new Comparator<TestDto>() {
            @Override
            public int compare(TestDto o1, TestDto o2) {
                Date o1Date = o1.getRegistrationDateTime();
                Date o2Date = o2.getRegistrationDateTime();

                return o1Date.compareTo(o2Date);
            }
        });

    }
}

