package auth.domain.store;

import app.domain.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmpStore {
    public List<Employee> empList;
    //Constructor
    public EmpStore() {
        this.empList = new ArrayList<>();
    }
    //Get
    public EmpStore getEmployeeStore() {
        return this;
    }
    //Behavior Methods
    public boolean saveEmployee(Employee emp) {
        if (validateEmployee(emp)) {
            empList.add(emp);
            return true;
        } else
            return false;
    }
    public boolean validateEmployee(Employee emp) {
        if (empList.contains(emp))
            return false;
        else return true;
    }


}
