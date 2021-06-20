package auth.domain.store;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import net.sourceforge.barbecue.Barcode;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

/**
 * Represents the Test store class.
 *
 * @author Eduardo Gonçalves
 */
public class TestStore implements Serializable{

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
     * @param client that ordered the test
     * @param parameterCategoryList the test's list of Parameter Categories.
     * @param testParameterList the test's list of Test Parameters.
     * @param testType the test's Test Type.
     * @param code the test's code.
     * @param nhsCode the test's nhsCode.
     * @param existsTest used to generate a new code.
     * @param registerDate date.
     * @param chemicalAnalysisDate date.
     * @param diagnosisDate date.
     * @param validationDate date.
     * @return the success of the operation
     * @throws Exception
     */
    public Test createTest(Client client, List<ParameterCategory> parameterCategoryList, List<TestParameter> testParameterList, TestType testType, String code, String nhsCode, boolean existsTest, Date registerDate, Date chemicalAnalysisDate, Date diagnosisDate, Date validationDate) throws Exception {

        return new Test(client, parameterCategoryList, testParameterList, testType, code, nhsCode, existsTest, registerDate, chemicalAnalysisDate, diagnosisDate, validationDate);
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
            guardarFicheiroBinario(this);
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
            if (c.getStatus().equalsIgnoreCase("Analyzed"))
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
        return null;
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
            List<Sample> sampleList = test.getListSamples();
            for(Sample sample : sampleList){
                if(sample.getBarcode().equals(barcode))
                    return test;
            }
        }
        return null;
    }

    /**
     * Creates a list which is able to return a copy of all tests that have the IgGAN parameter higher than the established (i.e.: that test positive to covid-19).
     *
     * @return a list which is able to return a copy of all tests that have the IgGAN parameter higher than the established (i.e.: that test positive to covid-19).
     */
    public List<Test> getAllTestWithResultCovidPositive(Date date){
        List<Test> listTestWithResultCovidPositive = new ArrayList<>();


        for (Test t: TestList){


            if (t.getValidationDateTime().getDate() == date.getDate() && t.getValidationDateTime().getMonth() == date.getMonth() &&
                    t.getValidationDateTime().getYear() == date.getYear()) {

                if (t.getStatus().equalsIgnoreCase(Test.Status.Validated.toString())) {

                    for (TestParameter testParameter : t.getTestParameterList()) {

                        if (testParameter.getCode().equalsIgnoreCase("IgGAN")) {
                            TestParameterResult testParameterResult = testParameter.getTestParameterResult();
                            double minValue = testParameterResult.getReferenceValue().getMaxValue();

                            if (testParameterResult.getResult() > minValue)
                                listTestWithResultCovidPositive.add(t);
                        }
                    }
                }
            }

        }
        return listTestWithResultCovidPositive;
    }

    /**
     * By calling this method you know how many tests waiting for result the company has
     *
     * @return the number of tests waiting for result
     */
    public int getCollectedTestsNumber(Date beginning, Date end){
        List<Test> collectedTest=new ArrayList<>();
        for(Test t: TestList){
            if(t.getRegistrationDateTime().after(beginning)&&t.getRegistrationDateTime().before(end)){
                collectedTest.add(t);
            }
        }
        return collectedTest.size();
    }

    /**
     * By calling this method you know how many tests waiting for diagnosis the company has
     *
     * @return the number of tests waiting for diagnosis
     */
    public int getAnalysedTestsNumber(Date beginning, Date end){
        List<Test> analysedTest=new ArrayList<>();
        for(Test t: TestList){
            if(t.getChemicalAnalysisDateTime().after(beginning)&&t.getChemicalAnalysisDateTime().before(end)){
                analysedTest.add(t);
            }
        }
        return analysedTest.size();
    }

    /**
     * By calling this method you know how many performed tests the company has
     *
     * @return the number of performed tests
     */
    public int getValidatedTestsNumber(Date beginning, Date end){
        List<Test> validatedTest=new ArrayList<>();
        for(Test t: TestList){
            if(t.getValidationDateTime().after(beginning)&&t.getValidationDateTime().before(end)){
                validatedTest.add(t);
            }
        }
        return validatedTest.size();
    }

    /**
     * @param date
     *
     * Method that returns the number of daily performed tests according to a date.
     *
     * @return the integer which contains the number of daily performed tests
     */
    public int getDailyPerformedTests(Date date){
        int numTestes = 0;
        for(Test t: TestList){
            if(t.getValidationDateTime().getDate() == date.getDate() && t.getValidationDateTime().getMonth() == date.getMonth() &&
                    t.getValidationDateTime().getYear() == date.getYear()){
                numTestes++;
            }
        }
        return numTestes;
    }

    /**
     * @param lstAllTestWithResultCovidPositive Array with lists of Positive test results.
     *
     * Method that returns the number of daily positive tests on the array.
     *
     * @return a double array with a number of daily positive tests.
     */
    public double[] getDailyPositiveTests(List<Test> [] lstAllTestWithResultCovidPositive){
        double[] dailyNumberPositiveTests = new double[lstAllTestWithResultCovidPositive.length];
        for(int i = 0; i<lstAllTestWithResultCovidPositive.length; i++){
            dailyNumberPositiveTests[i] = lstAllTestWithResultCovidPositive[i].size();
        }
        return dailyNumberPositiveTests;
    }


    public List<Test> getAllTestWithResultCovid(Date date) {
        List<Test> listTestWithResultCovid = new ArrayList<>();


        for (Test t : TestList) {


            if (t.getValidationDateTime().getDate() == date.getDate() && t.getValidationDateTime().getMonth() == date.getMonth() &&
                    t.getValidationDateTime().getYear() == date.getYear()) {

                if (t.getStatus().equalsIgnoreCase(Test.Status.Validated.toString())) {

                    for (TestParameter testParameter : t.getTestParameterList()) {

                        if (testParameter.getCode().equalsIgnoreCase("IgGAN")) {
                            listTestWithResultCovid.add(t);
                        }
                    }
                }
            }

        }
        return listTestWithResultCovid;
    }


    public double[] getMeanAgeFromList(List<Test> [] lstAllTestWithResultCovid) throws ParseException {

        double[] meanAge = new double[lstAllTestWithResultCovid.length];

        for (int i = 0; i < lstAllTestWithResultCovid.length; i++) {
            double mediaCliente = 0;
            for (Test t : lstAllTestWithResultCovid[i]) {
                String birthString = t.getClient().getBirthDate();
                Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthString);
                Date now = new Date();

                LocalDate birthDateLocal = Utils.convertToLocalDateViaInstant(birthDate);
                LocalDate nowLocal = Utils.convertToLocalDateViaInstant(now);

                Period period = Period.between(birthDateLocal, nowLocal);

                mediaCliente += period.getYears();
            }
            meanAge[i] = mediaCliente / lstAllTestWithResultCovid[i].size();
        }
        return meanAge;

    }
    public boolean guardarFicheiroBinario(TestStore store) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("testStore.bin"));
            try {
                out.writeObject(store);
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public double[][] createBiarrayX (double[] dailyNumberTests, double[] meanAge){

        double[][] BiarrayX = new double[dailyNumberTests.length][Constants.NUM_COLUNAS];

        for (int i=0; i<dailyNumberTests.length; i++){
            BiarrayX[i][0] = 1;
            BiarrayX[i][1] = dailyNumberTests[i];
            BiarrayX[i][2] = meanAge[i];
        }
        return BiarrayX;
    }
}
