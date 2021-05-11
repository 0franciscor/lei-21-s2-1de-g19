package auth.domain.store;


import app.domain.model.Employee;
import app.domain.model.OrgRole;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmpStoreTest {


    @Test
    public void saveEmployee() {

        Employee emp = new Employee("Alexandre", new OrgRole("Recepcionist"), "rua1", "toberto@gmail.com", "12345678909", 1234);

        EmpStore empS = new EmpStore();

        boolean result = true;
        boolean expected = empS.saveEmployee(emp);

        Assert.assertEquals(result,expected);
    }

    @Test
    public void DoesNotsaveEmployee() {

        Employee emp = new Employee("Alexandre", new OrgRole("Recepcionist"), "rua1", "toberto@gmail.com", "12345678909", 1234);

        EmpStore empS = new EmpStore();

        boolean result = false;
        empS.empList.add(emp);
        boolean expected = empS.saveEmployee(emp);

        Assert.assertEquals(result,expected);
    }
    @Test
    public void validateEmployeeExists() {
        Employee emp = new Employee();
        EmpStore emp1 = new EmpStore();
        boolean expected = false;
        emp1.empList.add(emp);
        boolean result = emp1.validateEmployee(emp);
        assertEquals(expected, result);
    }
    @Test
    public void validateEmployeeDoesNotExist() {
        Employee emp = new Employee();
        EmpStore emp1 = new EmpStore();
        boolean expected = true;
        boolean result = emp1.validateEmployee(emp);
        assertEquals(expected, result);
    }

    @Test
    public void getEmpList() {
        EmpStore empStore = new EmpStore();
        List<Employee> expected = empStore.empList;
        List<Employee> result = empStore.getEmpList();
        assertEquals(expected, result);
    }
}