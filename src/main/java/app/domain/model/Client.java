package app.domain.model;

import auth.domain.model.User;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

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

    public Client (String citizenID, String nhsID, String birthDate,String sex, String TIN, String phoneNumber, String email, String name){

        checkCitizenIDRules (citizenID);
        checkNhsIDRules (nhsID);
        checkTinRules (TIN);
        checkBirthDateRules (birthDate);
        checkSexRules (sex);
        checkPhoneNumberRules (phoneNumber);
        checkNameRules (name);
        this.citizenID = citizenID;
        this.nhsID = nhsID;
        this.birthDate = birthDate;
        this.sex = sex;
        this.TIN = TIN;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
    }

    private void checkCitizenIDRules (String citizenID){

        if (StringUtils.isBlank(citizenID))
            throw new IllegalArgumentException("Citizen card number cannot be blank.");
        if ( citizenID.length() != 16 )
            throw new IllegalArgumentException("Citizen card number must have 16 chars.");
    }

    private void checkNhsIDRules (String nhsID){

        if (StringUtils.isBlank(nhsID))
            throw new IllegalArgumentException("NHS number cannot be blank.");
        if ( nhsID.length() != 10 )
            throw new IllegalArgumentException("NHS number must have 10 chars.");

    }

    private void checkTinRules (String TIN){

        if (StringUtils.isBlank(TIN))
            throw new IllegalArgumentException("TIN cannot be blank.");
        if ( TIN.length() != 10 )
            throw new IllegalArgumentException("TIN must have 10 chars.");

    }

    public boolean checkBirthDateRules (String birthDate){

        if (birthDate.trim().equals(""))
            throw new IllegalArgumentException("BirthDate cannot be null");
        else {

            SimpleDateFormat data = new SimpleDateFormat("DD/MM/YYYY");
            data.setLenient(false);

            try {

                Date javaDate = data.parse(birthDate);
                System.out.println(birthDate+ "is valid date format");

            } catch (ParseException e) {

                System.out.println(birthDate+ "is invalid date format");
                return false;
            }
            return true;
        }
    }

    private void checkSexRules (String sex){

        if (StringUtils.isBlank (sex))
        throw new IllegalArgumentException("Sex cannot be blank.");
    }

    private void checkPhoneNumberRules (String phoneNumber){

        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone number cannot be blank.");
        if ( phoneNumber.length() != 11 )
            throw new IllegalArgumentException("Phone number must have 11 chars.");

    }

    private void checkNameRules (String name) {

        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( name.length() > 35 )
            throw new IllegalArgumentException("Name must not have more than 35 chars.");
    }

    public String getCitizenID() {
        return citizenID;
    }

    public String getNhsID() {
        return nhsID;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public String getTIN() {
        return TIN;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
