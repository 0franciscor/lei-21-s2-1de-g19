package auth.mappers.dto;

import app.domain.model.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Client dto class.
 *
 * @author Eduardo Gonçalves
 */
public class ClientDto {

    /**
     * Client's citizen card number.
     */
    private String citizenID;

    /**
     * Client's National Healthcare Service number.
     */
    private String nhsID;

    /**
     * Client's birth date.
     */
    private String birthDate;

    /**
     * Client's address.
     */
    private String address;

    /**
     * Client's sex.
     */
    private String sex;

    /**
     * Client's Tax Identification number.
     */
    private String TIN;

    /**
     * Client's phone number.
     */
    private String phoneNumber;

    /**
     * Client's email.
     */
    private String email;

    /**
     * Client's name.
     */
    private String name;

    private List<Test> clientTestList;

    /**
     * Builds a clientDto instance, receiving the citizenID, nhsID, birthDate, sex, TIN, phoneNumber, email and name.
     *
     * @param citizenID Client's citizen card number
     * @param nhsID Client's National Healthcare Service number
     * @param birthDate Client's birth date
     * @param sex Client's sex
     * @param TIN Client's Tax Identification number
     * @param phoneNumber Client's phone number
     * @param email Client's email
     * @param name Client's name
     */
    public ClientDto (String citizenID, String nhsID, String birthDate, String sex, String TIN, String phoneNumber, String email, String name, String address){

        this.citizenID = citizenID;
        this.nhsID = nhsID;
        this.birthDate = birthDate;
        this.sex = sex;
        this.TIN = TIN;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.address = address;

    }
    public ClientDto (String nome, String tin) {
        this.name = nome;
        this.TIN = tin;
    }
    public ClientDto (String name, String sex, String tin, String birthDate, List<Test> testList) {
        this.name = name;
        this.sex = sex;
        this.TIN = tin;
        this.birthDate = birthDate;
        this.clientTestList = testList;
    }

    /**
     * Returns the Client's citizen card number.
     *
     * @return Client's citizen card number.
     */
    public String getCitizenID(){
        return citizenID;
    }

    /**
     * Returns the Client's National Healthcare Service number.
     *
     * @return Client's National Healthcare Service number.
     */
    public String getNhsID(){
        return nhsID;
    }

    /**
     * Returns the Client's birth date.
     *
     * @return Client's birth date.
     */
    public String getBirthDate(){
        return birthDate;
    }

    /**
     * Returns the Client's sex.
     *
     * @return Client's sex.
     */
    public String getSex(){
        return sex;
    }

    /**
     * Returns the Client's Tax Identification number.
     *
     * @return Client's Tax Identification number.
     */
    public String getTIN(){
        return TIN;
    }

    /**
     * Returns the Client's phone number.
     *
     * @return Client's phone number.
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * Returns the Client's email.
     *
     * @return Client's email.
     */
    public String getEmail(){
        return email;
    }

    /**
     * Returns the Client's Address.
     *
     * @return Client's address
     */
    public String getAddress(){
        return address;
    }

    /**
     * Returns the Client's name.
     *
     * @return Client's name.
     */
    public String getName(){
        return name;
    }
    public List<Test> getTestsOfClient() {
        List<Test> listOfTestsValidated = new ArrayList<>();
        for (Test c : this.clientTestList)
            if (c.getValidationDateTime() != null)
                listOfTestsValidated.add(c);
        return listOfTestsValidated;
    }
}
