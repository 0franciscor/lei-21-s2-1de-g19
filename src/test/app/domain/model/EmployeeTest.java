package app.domain.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void checkNameRulesValid() {
        Employee emp = new Employee("Alex", new OrgRole("SpecDoctor"), "Rua 1", "alex.williams.soares@gmail.com", "12345678909", 1234, 123456);
        String name = "Alex Soares";
        emp.checkNameRules(name);
    }

    @Test
    public void checkPhoneNumberRules() {
    }

    @Test
    public void checkSOCCODERules() {
    }

    @Test
    public void checkDoctorIndexNumberRules() {
    }

    @Test
    public void generateID() {
    }

    @Test
    public void generatePwd() {
    }
}