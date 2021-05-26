package auth.domain.store;

import app.domain.model.Parameter;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.model.TestType;
import net.sourceforge.barbecue.Barcode;

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
    private List<Test> TestList;

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

        if (hasTest(test)){
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
    public boolean hasTest(Test test){
        if (TestList.contains(test))
            return false;
        else
            return true;
    }


    public List<Test> getAnalyzedTests() {
        List<Test> analyzedTestsList = new ArrayList<Test>();
        for (Test c : TestList)
            if (c.getStatus().toString() == Test.Status.Analyzed.toString())
                analyzedTestsList.add(c);

        return analyzedTestsList;
    }

    public List<Test> getRegisteredTests(){
        List<Test> registeredTests = new ArrayList<>();
        for(Test t: TestList){
            if(t.getStatus().equals("Registered"))
            registeredTests.add(t);
        }
        return registeredTests;
    }

    public Test getTest(String code){
        for(Test t: TestList){
            if(code.equals(t.getCode()))
                return t;
        }
        return null;
    }

    public boolean globallyUnique(List<Barcode> listBarcodes){

        for(Test t: TestList){
            List<Sample> sampleList = t.getListSamples();
            for(Sample s: sampleList){
                for(Barcode b: listBarcodes){
                    if(s.getBarcode().equals(b))
                        return false;
                }
            }
        }
        return true;
    }


    /**
     *
     * @return all tests that exist in the test list
     */
    public List<Test> SeeList (){
        return TestList;
    }

    /**
     * A method responsible for returning a list of tests that are reported, which means they are ready to be validated.
     *
     * @return a list of tests ready to be validated by the laboratory coordinator.
     */
    public List<Test> getDiagnosedTests() {
        List<Test> diagnosedTestsList = new ArrayList<>();
        for (Test test : TestList){
            if (test != null && test.getStatus().equalsIgnoreCase(Test.Status.Reported.toString()))
                diagnosedTestsList.add(test);
        }
        return diagnosedTestsList;
    }

    /**
     * @param code that identifies a test.
     *
     * Method responsible for fetching a test through its code.
     *
     * @return the test which is identified by the code or a new test.
     */
    public Test getTestByCode(String code){
        for(Test test : TestList) {
            if (test.getCode().equalsIgnoreCase(code))
                return test;
        }
        return new Test();
    }

    /**
     * @param test that the laboratory coordinator wants to validate.
     *
     * Method responsible for validating a test after a report has been validated.
     *
     * @return success of the operation (true if successful and false if not).
     */
    public boolean validateTest(Test test){
        return test.updateValidationDateTime();
    }
}
