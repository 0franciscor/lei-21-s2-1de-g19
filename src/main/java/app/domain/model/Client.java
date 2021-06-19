package app.domain.model;

import app.controller.App;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents the Client class.
 *
 * @author Eduardo Gonçalves
 */
public class Client implements Serializable {

    /**
     * Client's citizen card number.
     */
    private String citizenID;

    /**
     * Client's National Healthcare Service number.
     */
    private String nhsID;

    /**
     * The Client's address.
     */
    private String address;

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

    private List<Test> clientTestsList;

    /**
     * The client's password
     */
    private String password;


    /**
     * Builds a client instance, receiving the citizenID, nhsID, birthDate, sex, TIN, phoneNumber, email and name.
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
    public Client (String citizenID, String nhsID, String birthDate,String sex, String TIN, String phoneNumber, String email, String name, String address){

        checkCitizenIDRules (citizenID);
        checkNhsIDRules (nhsID);
        checkTinRules (TIN);
        checkBirthDateRules (birthDate);
        checkSexRules (sex);
        checkPhoneNumberRules (phoneNumber);
        checkNameRules (name);
        checkAddressRules(address);
        this.citizenID = citizenID;
        this.nhsID = nhsID;
        this.birthDate = birthDate;
        this.sex = sex;
        this.TIN = TIN;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.address = address;
        this.clientTestsList = new ArrayList<>();
    }

    /**
     * Builds an empty Client instance.
     */
    public Client (){

    }

    /**
     * Validates the acceptance criteria of the Client's citizen card number.
     */
    public void checkCitizenIDRules (String citizenID){

        if (StringUtils.isBlank(citizenID))
            throw new IllegalArgumentException("Citizen card number cannot be blank.");
        if ( citizenID.length() != 16 )
            throw new IllegalArgumentException("Citizen card number must have 16 chars.");
        for (int i = 0; i<citizenID.length(); i++){
            char y = citizenID.charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 ){
                throw new IllegalArgumentException("Citizen card number must be only numbers.");
            }
        }
    }

    /**
     * Validates the acceptance criteria of the Client's National Healthcare Service number.
     */
    public void checkNhsIDRules (String nhsID){

        if (StringUtils.isBlank(nhsID))
            throw new IllegalArgumentException("NHS number cannot be blank.");
        if ( nhsID.length() != 10 )
            throw new IllegalArgumentException("NHS number must have 10 chars.");
        for (int i = 0; i<nhsID.length(); i++){
            char y = nhsID.charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 ){
                throw new IllegalArgumentException("NHS number must be only numbers.");
            }
        }

    }

    /**
     * Validates the acceptance criteria of the Client's Tax Identification number.
     */
    public void checkTinRules (String TIN){

        if (StringUtils.isBlank(TIN))
            throw new IllegalArgumentException("TIN cannot be blank.");
        if ( TIN.length() != 10 )
            throw new IllegalArgumentException("TIN must have 10 chars.");
        for (int i = 0; i<TIN.length(); i++){
            char y = TIN.charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 ){
                throw new IllegalArgumentException("TIN must be only numbers.");
            }
        }

    }

    /**
     * Validates the acceptance criteria of the Client's birth date.
     */
    public boolean checkBirthDateRules (String birthDate){

        if (birthDate.trim().equals(""))
            throw new IllegalArgumentException("BirthDate cannot be null");
        else {

            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
            data.setLenient(false);

            try {
                Date javaDate = data.parse(birthDate);
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(javaDate);
                int year = cal.get(Calendar.YEAR);
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                if (year > currentYear || year <= (currentYear - 150))
                    throw new IllegalArgumentException("Birth date is incorrect.");

            } catch (ParseException e) {

                throw new IllegalArgumentException("Birth date is incorrect.");
            }
        }
        return true;
    }

    /**
     * Validates the acceptance criteria of the Client's sex.
     */
    public void checkSexRules (String sex){

        if (StringUtils.isBlank (sex))
            throw new IllegalArgumentException("Sex cannot be blank.");
        if (!sex.equalsIgnoreCase("male") && !sex.equalsIgnoreCase("female"))
            throw new IllegalArgumentException("Sex must be male or female");
    }

    /**
     * Validates the acceptance criteria of the Client's phone number.
     */
    public void checkPhoneNumberRules (String phoneNumber){

        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone number cannot be blank.");
        if ( phoneNumber.length() != 11 ){
            throw new IllegalArgumentException("Phone number must have 11 chars.");
        }
        for (int i = 0; i<phoneNumber.length(); i++){
            char y = phoneNumber.charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 ){
                throw new IllegalArgumentException("Phone number must be only numbers.");
            }
        }
    }

    /**
     * Validates the acceptance criteria of the Client's name.
     */
    public void checkNameRules (String name) {

        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( name.length() > 35 )
            throw new IllegalArgumentException("Name must not have more than 35 chars.");
        for (int i = 0; i<name.length(); i++){
            char y = name.charAt(i);
            if (y == 48 || y == 49 || y == 50 || y == 51 || y == 52 || y == 53 || y == 54 || y == 55 || y == 56 || y == 57 ){
                throw new IllegalArgumentException("Name must be only letters.");
            }
        }
    }

    /**
     * Validates the acceptance criteria of the Client's Address.
     */
    public void checkAddressRules (String address){
        if(StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
        if (address.length() > 90 )
            throw new IllegalArgumentException("Address must not have more than 90 chars.");
        }

    /**
     * Returns the Client's citizen card number.
     *
     * @return Client's citizen card number.
     */
    public String getCitizenID() {
        return citizenID;
    }

    /**
     * Returns the Client's National Healthcare Service number.
     *
     * @return Client's National Healthcare Service number.
     */
    public String getNhsID() {
        return nhsID;
    }

    /**
     * Returns the Client's birth date.
     *
     * @return Client's birth date.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Returns the Client's sex.
     *
     * @return Client's sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Returns the Client's Tax Identification number.
     *
     * @return Client's Tax Identification number.
     */
    public String getTIN() {
        return TIN;
    }

    /**
     * Returns the Client's phone number.
     *
     * @return Client's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the Client's email.
     *
     * @return Client's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the Client's name.
     *
     * @return Client's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the client's address.
     *
     * @return Client's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Changes the Client's sex.
     *
     * @param sex new Client's sex
     */
    public void setSex(String sex) {
        checkSexRules(sex);
        this.sex = sex;
    }

    /**
     * Changes the Client's phone number.
     *
     * @param phoneNumber new Client's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        checkPhoneNumberRules(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Changes the Client's email.
     *
     * @param email new Client's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Changes the Client's name.
     *
     * @param name new Client's name
     */
    public void setName(String name) {
        checkNameRules(name);
        this.name = name;
    }

    /**
     * Returns a random password with 10 characters.
     *
     * @return random password
     */
    public String generatePwd(){

        Random random = new Random();

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String strAux = "";

        for (int i=0; i<10; i++){

            int x = 1 + random.nextInt(61);
            char aux = str.charAt(x);
            strAux += aux;
        }
        this.password = strAux;
        return strAux;
    }
    public void addTest(Test test) {
        this.clientTestsList.add(test);
    }
    public List<Test> getClientTestsList() {
        return this.clientTestsList;
    }

    public void sendNotification() throws IOException {
        App.getInstance().getCompany().getNotificationService().writeToFile(String.format("Dear User, your password is %s and your email is %s", password, email));
    }
}
