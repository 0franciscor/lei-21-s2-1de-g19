package auth.domain.store;

import app.domain.model.Employee;
import app.domain.model.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents an Employee Store.
 *
 * @author Alexandre Soares
 */
public class EmpStore implements Serializable {
    /**
     * The Employee Store list.
     */
    public List<Employee> empList;
    /**
     * Builds a new EmployeeStore object defining the empList as a new ArrayList.
     *
     */
    public EmpStore() {
        this.empList = new ArrayList<>();
    }
    /**
     * Method responsible to return this EmployeeStore object.
     *
     * @return EmpStore
     */
    public EmpStore getEmployeeStore() {
        return this;
    }
    /**
     * Method responsible to save an Employee object on the empList of this EmployeeStore object.
     *
     * @param emp the Employee to be saved on the empList.
     *
     * @return boolean.
     */
    public boolean saveEmployee(Employee emp) {
        if (validateEmployee(emp)) {
            empList.add(emp);
            guardarFicheiroBinario(this);
            return true;
        } else
            return false;
    }
    /**
     * Method responsible to validate an Employee object. It assures that this Employee object does not exist on the empList of
     * EmpStore object.
     *
     * @param emp the Employee to be validated.
     *
     * @return boolean.
     */
    public boolean validateEmployee(Employee emp) {
        if (empList.contains(emp))
            return false;
        else return true;
    }

    /**
     * Method responsible to get the empList (List of Employees) of this EmpStore object.
     *
     * @return empList a List<Employee>.
     */
    public List<Employee> getEmpList() {
        return empList;
    }
    public boolean guardarFicheiroBinario(EmpStore store) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("empStore.bin"));
            try {
                out.writeObject(store);
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }
}
