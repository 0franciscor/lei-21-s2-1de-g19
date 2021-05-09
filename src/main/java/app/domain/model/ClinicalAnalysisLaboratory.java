package app.domain.model;

import java.util.List;

/**
 * Represents the ClinicalAnalysisLaboratory class
 *
 * @author Nuno Pinho (1190918)
 */
public class ClinicalAnalysisLaboratory {

    /**
     *
     * The laboratory's name.
     *
     */
    private String name;

    /**
     *
     * The laboratory's address.
     *
     */
    private String address;

    /**
     *
     * The laboratory's phone number.
     *
     */
    private String phoneNumber;

    /**
     *
     * The laboratory's TIN.
     *
     */
    private String TIN;

    /**
     *
     * The laboratory's ID.
     *
     */
    private String labID;

    /**
     *
     * The Test Types List.
     *
     */
    private List<TestType> TestTypesList;

    /**
     * Constructs a Clinical Analysis Laboratory's instance, receiving the lab's
     * name, address, phone number, TIN, the ID and the Test Type List
     *
     * @param name
     * @param address
     * @param phoneNumber
     * @param TIN
     * @param labID the lab ID
     */
    public ClinicalAnalysisLaboratory(String name, String address, String phoneNumber, String TIN, String labID, List<TestType> TestTypesList) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.TIN = TIN;
        this.labID = labID;
        this.TestTypesList = TestTypesList;
    }

    /**
     * Modifies the lab ID
     *
     * @param labID the new lab ID
     */
    /**
     * FALTA SABER O MAX LENGTH DO LAB ID public void setLabID(String labID) {
     * if (StringUtils.isBlank(labID)) { throw new IllegalArgumentException("Lab
     * ID cannot be empty."); } if (labID.length() > LABID_MAX_LENGTH) {
     *
     * throw new IllegalArgumentException("The Lab ID length is not correct.");
     * } this.labID = labID; }
     */
    /**
     * Gives the name.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gives the address.
     *
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Gives the phone number.
     *
     * @return phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Gives the TIN.
     *
     * @return TIN
     */
    public String getTIN() {
        return this.TIN;
    }

    /**
     * Gives the lab ID
     *
     * @return labID
     */
    public String getLabID() {
        return this.labID;
    }

    /**
     * Gives the Test Types List.
     *
     * @return TestTypesList
     */
    public List<TestType> getTestTypesList() {
        return TestTypesList;
    }

    /**
     * Gives the name, address, phone number, TIN, ID and the Test Type/Test
     * Types
     *
     * @return name, phoneNumber, TIN, labID, TestType/TestTypesList
     */
    @Override
    public String toString() {
        return String.format("The Laboratory's name is %s, the address is %s, the phone number is %s, the TIN is %s and the ID is %s. %s", this.name, this.address, this.phoneNumber, this.TIN, this.labID, this.TestTypesList);

    }
}

