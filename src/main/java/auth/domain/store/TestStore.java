package auth.domain.store;

import app.domain.model.*;
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
     * Allows storing National Healthcare Service numbers.
     */
    List<String> nhsCodeList = new ArrayList<>();

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
     * @param parameterCategories list of parameter categories
     * @param TIN Tax Identification number
     * @param nhsCode National Healthcare Service number
     * @return created Test
     */
    public Test createTest (TestType testType, List<Parameter> parameters, List<ParameterCategory> parameterCategories, String TIN, String nhsCode){

        return new Test(testType,parameters, parameterCategories, TIN, nhsCode);
    }

    /**
     * If the test does not belong to the test list adds it to that list, update the test status and returns true, otherwise returns false.
     *
     * @param test test to validate
     * @return true if the test is not already on the test list, otherwise returns false.
     */
    public boolean saveTest (Test test){

        if (hasTest(test)){
            addTest(test);
            test.updateTestStatus();
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

    /**
     * Method responsible for retrieving a list of Analyzed tests.
     *
     * @return a list of Analyzed tests.
     */
    public List<Test> getAnalyzedTests() {
        List<Test> analyzedTestsList = new ArrayList<Test>();
        for (Test c : TestList)
            if (c.getStatus().equalsIgnoreCase(Test.Status.Analyzed.toString()))
                analyzedTestsList.add(c);

        return analyzedTestsList;
    }

    /**
     * Creates a list with all the tests which status is "Registered"
     * @return the list
     */
    public List<Test> getRegisteredTests(){
        List<Test> registeredTests = new ArrayList<>();
        for(Test t: TestList){
            if(t.getStatus().equals("Registered"))
            registeredTests.add(t);
        }
        return registeredTests;
    }

    /**
     * Sees in all the existing tests if there is any barcode repeated
     *
     * @param listBarcodes
     * @return true if there are no barcodes repeated in all the tests
     * @return false if there is, at least, a barcode repeated in all the tests
     */
    public boolean globallyUnique(List<Barcode> listBarcodes){

        for(Test t: TestList){
            List<Sample> sampleList = t.getListSamples();
            if(sampleList.size()>0){
                for(Sample s: sampleList){
                    for(Barcode b: listBarcodes){
                        if(s.getBarcode().getData().equals(b.getData()))
                            return false;
                    }
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
     *
     * @return all National Healthcare Service numbers that exist in the nhsCode list
     */
    public List<String> getNhsCodeList (){
        return nhsCodeList;
    }

    /**
     * Method responsible for retrieving a list of tests which have had its samples collected.
     *
     * @return a list of tests which have had its samples collected.
     */
    public List<Test> getCollectedTests() {
        List<Test> collectedTestsList = new ArrayList<>();
        for (Test test : TestList){
            if (test != null && test.getStatus().equalsIgnoreCase(Test.Status.Collected.toString()))
                collectedTestsList.add(test);
        }
        return collectedTestsList;
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
     * Method responsible for returning a list of validated tests.
     *
     * @return a list of validated tests.
     */
    public List<Test> getValidatedTests() {
        List<Test> diagnosedTestsList = new ArrayList<>();
        for (Test test : TestList){
            if (test != null && test.getStatus().equalsIgnoreCase(Test.Status.Validated.toString()))
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

    /**
     * @param barcode that belongs as a sample of a test
     *
     * Method that is responsible for fetching a test from a list of tests.
     *
     * @return a test
     */
    public Test getTestBybarcodeWithPendingResults(Barcode barcode){
        for(Test test : TestList) {
            if (test.getListSamples().contains(barcode))
                return test;
        }
        return null;
    }


    /**
     * Creates a list which is able to return a copy of all tests that have the IgGAN parameter higher than the established (i.e.: that test positive to covid-19).
     *
     * @return a list which is able to return a copy of all tests that have the IgGAN parameter higher than the established (i.e.: that test positive to covid-19).
     */
    public List<Test> getAllTestWithResultCovidPositive(){

        List<Test> listTestWithResultCovidPositive = new ArrayList<>();

        for (Test t: TestList){
            if (t.getStatus().equalsIgnoreCase(Test.Status.Validated.toString())){
                for(TestParameter testParameter : t.getTestParameterList()){
                    if(testParameter.getCode().equalsIgnoreCase("IgGAN")) {
                        TestParameterResult testParameterResult = testParameter.getTestParameterResult();
                        double minValue = testParameterResult.getReferenceValue().getMinValue();
                        if (testParameterResult.getResult() > minValue)
                            listTestWithResultCovidPositive.add(t);
                    }
                }
            }
        }
        return listTestWithResultCovidPositive;
    }
}
