package app.controller;

import app.domain.model.Employee;
import app.domain.model.OrgRole;
import org.junit.Assert;
import org.junit.Test;

public class RegisterNewEmployeeControllerTest {

    @Test
    public void saveEmployeeTrue() {
        Employee emp = new Employee("Alexandre", new OrgRole("Recepcionist"), "rua1", "toberto@gmail.com", "12345678909", 1234);

        RegisterNewEmployeeController ctrl = new RegisterNewEmployeeController();

        boolean result = true;
        boolean expected = ctrl.saveEmployee(emp);

        Assert.assertEquals(result,expected);
    }
    public void saveEmployeeFalse() {
        Employee emp = new Employee("Alexandre", new OrgRole("Recepcionist"), "rua1", "toberto@gmail.com", "12345678909", 1234);

        RegisterNewEmployeeController ctrl = new RegisterNewEmployeeController();

        boolean result = false;
        ctrl.empStore.empList.add(emp);
        boolean expected = ctrl.saveEmployee(emp);

        Assert.assertEquals(result,expected);
    }
}