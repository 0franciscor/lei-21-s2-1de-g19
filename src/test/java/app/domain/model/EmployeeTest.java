package app.domain.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    @org.junit.Test
    public void checkNameRulesValid() {

        Employee emp = new Employee();

        String name = "João";
        emp.checkNameRules(name);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNameRulesInvalidBlank() {

        Employee emp = new Employee();

        String name = "";
        emp.checkNameRules(name);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNameRulesInvalidLength() {

        Employee emp = new Employee();

        String name = "João de Castro Pestana Cândido de Almeida";
        emp.checkNameRules(name);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkNameRulesInvalidNumbers() {

        Employee emp = new Employee();

        String name = "João8";
        emp.checkNameRules(name);
    }
    @org.junit.Test
    public void checkPhoneNumberRulesValid() {

        Employee emp = new Employee();

        String phoneNumber = "12345678909";
        emp.checkPhoneNumberRules(phoneNumber);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkPhoneNumberRulesInvalidBlank() {

        Employee emp= new Employee();

        String phoneNumber = "";
        emp.checkPhoneNumberRules(phoneNumber);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkPhoneNumberRulesInvalidLengthMoreThan11() {

        Employee emp = new Employee();

        String phoneNumber = "123456789091";
        emp.checkPhoneNumberRules(phoneNumber);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkPhoneNumberRulesInvalidLengthLessThan11() {

        Employee emp = new Employee();

        String phoneNumber = "1234567891";
        emp.checkPhoneNumberRules(phoneNumber);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkPhoneNumberRulesInvalidLetters() {

        Employee emp = new Employee();

        String phoneNumber = "12345678909P";
        emp.checkPhoneNumberRules(phoneNumber);
    }

    @Test
    public void checkSOCCODERules() {
        Employee emp = new Employee();
        int socCode = 1234;
        emp.checkSOCCODERules(socCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkSOCCODERulesisBlank() {
        Employee emp = new Employee();
        int socCode = Integer.parseInt(null);
        emp.checkSOCCODERules(socCode);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkSOCCODERulesLenght() {
        Employee emp = new Employee();
        int socCode = 12345;
        emp.checkSOCCODERules(socCode);
    }

    @Test
    public void checkDoctorIndexNumberRules() {
        Employee emp = new Employee();
        int socCode = 123424;
        emp.checkDoctorIndexNumberRules(socCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkDoctorIndexNumberRulesisBlank() {
        Employee emp = new Employee();
        int socCode = Integer.parseInt(null);
        emp.checkDoctorIndexNumberRules(socCode);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checkDoctorIndexNumberRulesLenght() {
        Employee emp = new Employee();
        int socCode = 12345;
        emp.checkDoctorIndexNumberRules(socCode);
    }

    @Test
    public void generateID() {
        Employee emp = new Employee();
        String expected = "AWS00003";
        String name = "Alex Williams Soares";
        String result = emp.generateID(name);
        assertEquals(expected.trim(),String.valueOf(result).trim());
    }

    @Test
    public void generatePwd() {

        Employee emp = new Employee();

        int passwordLengthResult = 10;

        String randomPassword = emp.generatePwd();
        int passwordLengthExpected = randomPassword.length();

        assertEquals(passwordLengthResult,passwordLengthExpected);
    }
}