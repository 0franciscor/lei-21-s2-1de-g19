package app.controller;

import app.domain.model.Company;
import auth.AuthFacade;
import auth.domain.store.EmpStore;
import app.domain.model.Employee;
import app.domain.model.OrgRole;
import auth.domain.model.Password;
import auth.mappers.RolesMapper;
import auth.mappers.dto.EmployeeDto;
import auth.mappers.dto.OrgRoleDto;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

import static auth.mappers.RolesMapper.toDto;

public class RegisterNewEmployeeController {
    private App app;
    public Company company;
    public AuthFacade authFacade;
    public EmpStore empStore;

    public RegisterNewEmployeeController(Company company) {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.authFacade = company.getAuthFacade();;

    }


    public Employee createEmployee(EmployeeDto empDto) {
            return this.company.createEmployee(empDto);
    }

    public boolean saveEmployee(Employee emp) {
        this.empStore = company.getEmployeeStore();
        if (this.empStore.saveEmployee(emp)) {
            String pwd = generatePwd();
            this.authFacade.addUserWithRole(emp.name, emp.email, pwd, emp.role.designation);
            return true;
        }
        return false;
    }

    public String generatePwd() {
        Random random = new Random();

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String strAux = "";

        for (int i=0; i<10; i++){

            int x = 1 + random.nextInt(61);
            char aux = str.charAt(x);
            strAux += aux;
        }

        return strAux;
    }

    public List<OrgRoleDto> getRoles(List<OrgRole> list) {
        List<OrgRole> lRoles = this.company.getRoles();
        return toDto(lRoles);
    }


}