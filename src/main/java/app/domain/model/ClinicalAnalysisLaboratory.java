package app.domain.model;

import org.apache.commons.lang3.StringUtils;

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
     * The TIN's exact length.
     */
    private static final int TIN_LENGTH = 10;

    private static final int LABID_LENGTH = 5;

    /**
     * Constructs a Clinical Analysis Laboratory's instance, receiving the lab's
     * name, address, phone number, TIN, the ID and the Test Type List
     *
     * @param name
     * @param address
     * @param phoneNumber
     * @param TIN
     * @param labID the lab ID
     * @param TestTypesList
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
     * Modifies and checks the inserted name.
     *
     * @param name
     */
    public void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    /**
     * Modifies and checks the inserted address.
     *
     * @param address
     */
    public void setAddress(String address) {
        if (StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
        this.address = address;
    }

    /**
     * Modifies and checks the inserted phone number.
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            throw new IllegalArgumentException("Phone Number cannot be empty.");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Modifies and checks the inserted TIN.
     *
     * @param TIN
     */
    public void setTIN(String TIN) {
        if (StringUtils.isBlank(TIN)) {
            throw new IllegalArgumentException("TIN cannot be empty.");
        }
        if (TIN.length() != TIN_LENGTH) {
            throw new IllegalArgumentException("The TIN length is not correct.");
        }
        this.TIN = TIN;
    }

    /**
     * Modifies and checks the inserted labID.
     *
     * @param labID
     */
    public void setLabID(String labID) {
        if (StringUtils.isBlank(labID) || labID.length() != LABID_LENGTH) {
            throw new IllegalArgumentException("Lab ID cannot be empty.");
        }
        this.labID = labID;
    }

    /**
     * Modifies and checks the inserted test types list.
     *
     * @param TestTypesList
     */
    public void setTestTypesList(List<TestType> TestTypesList) {
        if (TestTypesList == null || TestTypesList.isEmpty()) {
            throw new NullPointerException("The test types list is null.");
        }
        this.TestTypesList = TestTypesList;
    }

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