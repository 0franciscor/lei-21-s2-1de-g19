package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.OrgRole;
import auth.AuthFacade;
import auth.domain.store.EmpStore;
import auth.mappers.dto.EmployeeDto;
import auth.mappers.dto.OrgRoleDto;

import java.util.List;

import static auth.mappers.RolesMapper.toDto;
/**
 * Represents the controller that serves at intermediary between the UI and the domain layer.
 *
 * @author Alexandre Soares
 */
public class RegisterNewEmployeeController {
    /**
     * The controller´s App.
     */
    private App app;
    /**
     * The controller´s Company.
     */
    public Company company;
    /**
     * The controller´s Authentication Facade.
     */
    public AuthFacade authFacade;
    /**
     * The controller´s Employee Store.
     */
    public EmpStore empStore;
    /**
     * Builds a new RegisterNewEmployeeController object.
     *
     */
    public RegisterNewEmployeeController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.authFacade = company.getAuthFacade();;
        this.empStore = company.getEmployeeStore();
    }

    /**
     * Calls the createEmployee method from the company and returns an Employee object
     *
     * @param empDto a Data Transfer Object of Employee, which will have all the
     * info to create an Employee object
     * @return An Employee object.
     */
    public Employee createEmployee(EmployeeDto empDto) {
            return this.company.createEmployee(empDto);
    }
    /**
     * Calls the saveEmployee method from the company and returns a boolean.
     *
     * @param emp an Employee object.
     *
     * @return a Boolean.
     */
    public boolean saveEmployee(Employee emp) {
        if (this.empStore.saveEmployee(emp)) {
            String empId = emp.generateID(emp.name);
            emp.setEmpID(empId);
            String pwd = emp.generatePwd();
            this.authFacade.addUserWithRole(emp.name, emp.email, pwd, emp.role.designation);
            return true;
        }
        return false;
    }
    /**
     * Calls the getRoles method from the company and the toDto method from the RolesMapper.
     *
     * @return a List of OrgRole Data Transfer Object.
     */
    public List<OrgRoleDto> getRoles() {
        List<OrgRole> lRoles = this.company.getRoles();
        return toDto(lRoles);
    }


}