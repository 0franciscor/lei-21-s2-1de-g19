package auth.domain.store;

import app.domain.model.Parameter;
import app.domain.model.Test;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Test store class.
 *
 * @author Eduardo Gonçalves
 */
public class TestStore {

    /**
     * List of tests.
     */
    List<Test> TestList;

    /**
     * Citizen card number.
     */
    private String citizenID;

    /**
     * Builds a TestStore without receiving parameters.
     */
    public TestStore (){

        this.TestList = new ArrayList<>();
    }

    /**
     * Invokes the Test Builder.
     *
     * @param testType test type
     * @param parameters list of parameters
     * @param citizenID citizen card number
     * @return created Test
     */
    public Test createTest (TestType testType, List<Parameter> parameters, String citizenID, String nhsCode){

        return new Test(testType,parameters, citizenID, nhsCode);
    }

    /**
     * If the test does not belong to the test list adds it to that list and returns true, otherwise returns false.
     *
     * @param test test to validate
     * @return true if the test is not already on the test list, otherwise returns false.
     */
    public boolean saveTest (Test test){

        if (validateTest(test)){
            addTest(test);
            return true;
        }
        return false;
    }

    /**
     * Add a test to the test list.
     *
     * @param test test to add
     */
    public void addTest (Test test){

        TestList.add(test);
    }

    /**
     * If the test list contains the test returns false, otherwise returns true.
     *
     * @param test test to check
     * @return false if the test belongs to the test list, otherwise returns true.
     */
    public boolean validateTest (Test test){

        if (TestList.contains(test))
            return false;
        else
            return true;
    }

    /*
    public List<Test> getAnalyzedTests() {
        List<Test> analyzedTestsList = new ArrayList<Test>();
        for (Test c : TestList)
            if (c.getStatus() = c.Status.Analyzed)
                analyzedTestsList.add(c);

        return analyzedTestsList;
    }

     */

    /**
     *
     * @return all tests that exist in the test list
     */
    public List<Test> SeeList (){

        return TestList;

    }
}
