package app.domain.model;

import app.controller.App;
import auth.domain.store.ClientStore;
import net.sourceforge.barbecue.Barcode;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents the Test class.
 *
 * @author Eduardo Gonçalves
 */
public class Test {

    /**
     * Test's description.
     */
    private String description;

    /**
     * Test's code.
     */
    private String code;

    /**
     * Test's National Healthcare Service number.
     */
    private String nhsCode;

    /**
     * Test's registration date and time.
     */
    private Date registrationDateTime;

    /**
     * Test's collect date and time.
     */
    private Date collectDateTime;

    /**
     * Test's chemical analysis date and time.
     */
    private Date chemicalAnalysisDateTime;

    /**
     * Test's diagnosis date and time.
     */
    private Date diagnosisDateTime;

    /**
     * Test's validation date and time.
     */
    private Date validationDateTime;

    /**
     * The test type.
     */
    private TestType testType;

    /**
     * The parameters list.
     */
    private List<Parameter> parameters;

    private List<ParameterCategory> parameterCategories;

    private List<ParameterResult> parameterResults;
    /**
     * Client.
     */
    private Client client;

    /**
     * Company.
     */
    private Company company;

    /**
     * The client store.
     */
    private ClientStore clientStore;

    /**
     * The test's status.
     */
    private Status state;


    /**
     * The test's sample.
     */
    private Sample sample;

    /**
     * The test's list of samples.
     */
    private ArrayList<Sample> listSamples;

    /**
     * The test's list of barcodes.
     */
    private List<Barcode> testBarcodesList;

    /**
     * The available states for a test.
     */
    public static enum Status {
        Registered, Collected, Analyzed, Reported, Validated
    }

    /**
     * Builds an empty Test instance, which only registers the created state.
     */
    public Test() {
        this.state = Status.Registered;
        this.registrationDateTime = new Date();
    }

    /**
     * Builds a test instance, receiving the testType, parameters, parameterCategories, TIN and nhsCode.
     *
     * @param testType test type
     * @param parameters parameters list
     * @param parameterCategories parameter categories list
     * @param TIN Client's Tax Identification number
     * @param nhsCode National Healthcare Service number
     */
    public Test (TestType testType, List<Parameter> parameters, List<ParameterCategory> parameterCategories, String TIN, String nhsCode){

        checknhsCodeRules(nhsCode);
        this.company = App.getInstance().getCompany();
        this.testType = testType;
        this.parameters = parameters;
        this.parameterCategories = parameterCategories;
        this.parameterResults = new ArrayList<ParameterResult>();
        this.clientStore = company.getClientStore();
        this.client = clientStore.getClient(TIN);
        this.nhsCode = nhsCode;
        this.state = Status.Registered;
        this.registrationDateTime = new Date();
    }

    /**
     * Validates the acceptance criteria of Test's National Healthcare Service number.
     */
    public void checknhsCodeRules (String nhsCode){

        if (StringUtils.isBlank(nhsCode))
            throw new IllegalArgumentException("NHS Code cannot be blank.");
        if ( nhsCode.length() != 12 ){
            throw new IllegalArgumentException("NHS Code must have 12 chars.");
        }
        for (int i = 0; i<nhsCode.length(); i++){
            char y = nhsCode.charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 && y != 65 && y != 66 && y != 67 && y != 68 && y != 69
                    && y != 70 && y != 71 && y != 72 && y != 73 && y != 74 && y != 75 && y != 76 && y != 77 && y != 78 && y != 79 && y != 80 && y != 81 && y != 82 && y != 83
                    && y != 84 && y != 85 && y != 86 && y != 87 && y != 88 && y != 89 && y != 90 && y != 97 && y != 98 && y != 99 && y != 100 && y != 101 && y != 102 && y != 103
                    && y != 104 && y != 105 && y != 106 && y != 107 && y != 108 && y != 109 && y != 110 && y != 111 && y != 112 && y != 113 && y != 114 && y != 115 && y != 116 && y != 117
                    && y != 118 && y != 119 && y != 120 && y != 121 && y != 122){
                throw new IllegalArgumentException("NHS Code must be only numbers and letters.");
            }
        }
    }

    /**
     * Returns the Test's description.
     *
     * @return Test's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the Test's code.
     *
     * @return Test's code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the Test's National Healthcare Service number.
     *
     * @return Test's National Healthcare Service number.
     */
    public String getNhsCode() {
        return nhsCode;
    }

    /**
     * Returns the Test's registration date and time.
     *
     * @return Test's registration date and time.
     */
    public Date getRegistrationDateTime() {
        return registrationDateTime;
    }

    /**
     * Returns the Test's collect date and time.
     *
     * @return Test's collect date and time.
     */
    public Date getCollectDateTime() {
        return collectDateTime;
    }

    /**
     * Returns the Test's chemical analysis date and time.
     *
     * @return Test's chemical analysis date and time.
     */
    public Date getChemicalAnalysisDateTime() {
        return chemicalAnalysisDateTime;
    }

    /**
     * Returns the Test's diagnosis date and time.
     *
     * @return Test's diagnosis date and time.
     */
    public Date getDiagnosisDateTime() {
        return diagnosisDateTime;
    }

    /**
     * Returns the Test's validation date and time.
     *
     * @return Test's validation date and time.
     */
    public Date getValidationDateTime() {
        return validationDateTime;
    }

    /**
     * Returns the test type.
     *
     * @return test type.
     */
    public TestType getTestType() {
        return testType;
    }

    /**
     * Returns the parameters list.
     *
     * @return parameters list.
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Returns the Client.
     *
     * @return client.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Returns the Company.
     *
     * @return Company.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Returns the client store.
     *
     * @return client store.
     */
    public ClientStore getClientStore() {
        return clientStore;
    }

    /**
     * Returns the test's status.
     *
     * @return test's status.
     */
    public String getStatus() {
        return this.state.toString();
    }

    public ArrayList<Sample> getListSamples(){
        return this.listSamples;
    }

//    public String getValues() {
//        return values;
//    }

    /**
     * Returns the parameter categories list.
     *
     * @return parameter categories list.
     */
    public List<ParameterCategory> getParameterCategories() {
        return parameterCategories;
    }

    /**
     * Returns a textual representation of the object, which contains all of its attributes.
     *
     * @return Test characteristics.
     */
    public String toString (){
        //return String.format("Test with test type %s, parameters %s, citizen card number %s",this.testType, this.parameters, this.client.getCitizenID());
        return String.format("\nTest code: %s \nNHS code: %s \nRegistration date and time: %s ",this.code, this.nhsCode, this.registrationDateTime);
    }

    /**
     * Returns a sequential number code with 12 characters.
     *
     * @return sequential number code.
     */
    public void generateCode () {
        String code = "";
        int lengthCode = 12;
        String aux = String.valueOf(company.numTeste);


        for (int i=0; i < lengthCode - aux.length(); i++){

            code = code + "0";
        }
        code = code + (company.numTeste+1);
        company.numTeste++;

        this.code = code;

    }

    /**
     * A method that is responsible for automatically changing the Test validation date and time.
     *
     * @return the success of the operation (true if the operation was successful and false if not).
     */
    public boolean updateValidationDateTime(){
        try {
            if (App.getInstance().getCompany().getReportStore().getReportValidation(this.code)) {
                this.validationDateTime = new Date();
                updateTestStatus();
                return true;
            }
        } catch (Exception e){
            System.out.println("There was an error when updating the validation date and time. Please try again.");
            return false;
        }
        return false;
    }

    /**
     * Sees if there are 2 or more equal barcodes in the test barcodes list.
     *
     * @param listBarcodes
     * @return true if there are no repeated barcodes in the test
     * @return false if there is, at least, one barcode repeated in the test
     */
    public boolean isListUnique(List<Barcode> listBarcodes){
        for(Barcode b: listBarcodes){
            int no=0;
            for(Barcode b1: listBarcodes){
                if(b1.equals(b))
                    no++;
            }
            if(no>1)
                return false;
        }
        return true;
    }

    /**
     * Adds all the barcodes in the listBarcodes in the list of barcodes of the test and then calls the updateCollectDateTime method.
     * @param listBarcodes
     */
    public void addAll(List<Barcode> listBarcodes){
        for(Barcode b: listBarcodes){
            testBarcodesList.add(b);
        }
        updateCollectDateTime();
    }

    /**
     * Creates a list with new samples by calling the sample class.
     *
     * @return a list with the created samples
     */
    public List<Sample> create(){
        for(Barcode b: testBarcodesList){
            Sample sample=new Sample(b);
            listSamples.add(sample);
        }
        return listSamples;
    }


    /**
     * A method that is responsible for automatically changing the Test collect date and time.
     *
     * @return the success of the operation (true if the operation was successful and false if not).
     */
    public void updateCollectDateTime(){
        try{
            if(listSamples!=null){
                this.collectDateTime=new Date();
            }
        }catch(Exception e){
            System.out.println("There was an error when updating the validation date and time. Please try again.");
        }
    }

    /**
     * Calls the method to update the test status and checks if it is "Collected"
     *
     * @return true if the test was successfully updated
     * @return false if the test was not updated
     */
    public boolean validate(){
        if(getStatus().equals("Collected"))
            return true;
        return false;
    }


    /**
     * A method that is responsible for automatically changing the Test state.
     *
     */
    public void updateTestStatus(){
        try {
            if (this.getCollectDateTime() != null)
                state = Status.Collected;
            if (this.getChemicalAnalysisDateTime() != null)
                state = Status.Analyzed;
            if (this.getDiagnosisDateTime() != null)
                state = Status.Reported;
            if (this.getValidationDateTime() != null)
                state = Status.Validated;

        } catch (Exception e){
            System.out.println("There was an error when updating the Test Status. Please try again.");
        }
    }
   /* public boolean addParameterResult (Test test, Parameter parameter, String value, String metric) {
        parameterResults.add(new ParameterResult(test, parameter, value, metric));
        return true;
    }*/
    public List<ParameterResult> getParameterResults() {
        return parameterResults;
    }
}
