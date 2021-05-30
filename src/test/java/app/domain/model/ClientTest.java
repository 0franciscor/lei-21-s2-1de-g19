package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import static org.junit.Assert.*;

public class ClientTest {

    @org.junit.Test
    public void checkCitizenIDRulesValid() {

        Client cl = new Client();

        String resultCitizenID = "1234567890123456";
        cl.checkCitizenIDRules(resultCitizenID);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkCitizenIDRulesInvalidBlank() {

        Client cl = new Client();

        String resultCitizenID = "";
        cl.checkCitizenIDRules(resultCitizenID);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkCitizenIDRulesInvalidLength() {

        Client cl = new Client();

        String resultCitizenID = "1234";
        cl.checkCitizenIDRules(resultCitizenID);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkCitizenIDRulesInvalidLetters() {

        Client cl = new Client();

        String resultCitizenID = "1234LT";
        cl.checkCitizenIDRules(resultCitizenID);
    }


    @org.junit.Test
    public void checkNhsIDRulesValid() {

        Client cl = new Client();

        String nhsID = "1234567890";
        cl.checkNhsIDRules(nhsID);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNhsIDRulesInvalidBlank() {

        Client cl = new Client();

        String nhsID = "";
        cl.checkNhsIDRules(nhsID);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNhsIDRulesInvalidLength() {

        Client cl = new Client();

        String nhsID = "12";
        cl.checkNhsIDRules(nhsID);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNhsIDRulesInvalidLetters() {

        Client cl = new Client();

        String nhsID = "12U";
        cl.checkNhsIDRules(nhsID);
    }

    @org.junit.Test
    public void checkTinRulesValid() {

        Client cl = new Client();

        String TIN = "1234567890";
        cl.checkTinRules(TIN);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkTinRulesInvalidBlank() {

        Client cl = new Client();

        String TIN = "";
        cl.checkTinRules(TIN);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkTinRulesInvalidLength() {

        Client cl = new Client();

        String TIN = "123";
        cl.checkTinRules(TIN);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkTinRulesInvalidLetters() {

        Client cl = new Client();

        String TIN = "45875k";
        cl.checkTinRules(TIN);
    }

    @org.junit.Test
    public void checkBirthDateRulesValid() {

        Client cl = new Client();

        String birthDate = "12/04/2000";
        cl.checkBirthDateRules(birthDate);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkBirthDateRulesInvalidBlank() {

        Client cl = new Client();

        String birthDate = "";
        cl.checkBirthDateRules(birthDate);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkBirthDateRules() {

        Client cl = new Client();

        String birthDate = "31/02/2000";
        cl.checkBirthDateRules(birthDate);
    }

    @org.junit.Test
    public void checkSexRulesValidMale() {

        Client cl = new Client();

        String sex = "male";
        cl.checkSexRules(sex);
    }

    @org.junit.Test
    public void checkSexRulesValidFemale() {

        Client cl = new Client();

        String sex = "female";
        cl.checkSexRules(sex);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkSexRulesInvalidBlank() {

        Client cl = new Client();

        String sex = "";
        cl.checkSexRules(sex);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkSexRulesInvalidName() {

        Client cl = new Client();

        String sex = "test";
        cl.checkSexRules(sex);
    }

    @org.junit.Test
    public void checkPhoneNumberRulesValid() {

        Client cl = new Client();

        String phoneNumber = "12345678909";
        cl.checkPhoneNumberRules(phoneNumber);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkPhoneNumberRulesInvalidBlank() {

        Client cl = new Client();

        String phoneNumber = "";
        cl.checkPhoneNumberRules(phoneNumber);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkPhoneNumberRulesInvalidLength() {

        Client cl = new Client();

        String phoneNumber = "123456789091";
        cl.checkPhoneNumberRules(phoneNumber);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkPhoneNumberRulesInvalidLetters() {

        Client cl = new Client();

        String phoneNumber = "12345678909P";
        cl.checkPhoneNumberRules(phoneNumber);
    }

    @org.junit.Test
    public void checkNameRulesValid() {

        Client cl = new Client();

        String name = "João";
        cl.checkNameRules(name);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNameRulesInvalidBlank() {

        Client cl = new Client();

        String name = "";
        cl.checkNameRules(name);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNameRulesInvalidLength() {

        Client cl = new Client();

        String name = "João de Castro Pestana Cândido de Almeida";
        cl.checkNameRules(name);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNameRulesInvalidNumbers() {

        Client cl = new Client();

        String name = "João8";
        cl.checkNameRules(name);
    }

    @org.junit.Test
    public void generatePwd() {

        Client cl = new Client();

        int passwordLengthResult = 10;

        String randomPassword = cl.generatePwd();
        int passwordLengthExpected = randomPassword.length();

        assertEquals(passwordLengthResult,passwordLengthExpected);
    }

    @org.junit.Test
    public void Exemplo(){

        Client cl = new Client("1212121212121212","1212121212","12/12/2000","male","1212121212","12121212121","sdfs@gmail.com","Roberto");
        
        if (StringUtils.isBlank(cl.getCitizenID()))
            throw new IllegalArgumentException("Citizen card number cannot be blank.");
        if ( cl.getCitizenID().length() != 16 )
            throw new IllegalArgumentException("Citizen card number must have 16 chars.");
        for (int i = 0; i>=cl.getCitizenID().length(); i++){
            char y = cl.getCitizenID().charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 ){
                throw new IllegalArgumentException("Citizen card number must be only numbers.");
            }
        }
    }
}