package app.domain.model;

import app.controller.App;
import auth.mappers.dto.EmployeeDto;
import org.apache.commons.lang3.StringUtils;

public class Employee {
    public Company company;
    public String name;
    public OrgRole role;
    private String address;
    public String email;
    private String empID;
    private int phoneNumber;
    private int socCode;
    private int doctorIndexNumb;

    public Employee () {

    }
    public Employee (String name, String role, String address, String email,int phoneNumber, int socCode, int doctorIndexNumb)  {
        checkNameRules(name);
        checkRoleRules(role);
        this.company = App.getInstance().getCompany();
        this.name = name;
        this.role = company.getOrgRoleByName(role);
        this.address = address;
        this.email = email;
        this.empID = generateID(name);
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
        this.doctorIndexNumb = doctorIndexNumb;
        company.numEmp++;
    }
    public Employee (String name, String role, String address, String email, int phoneNumber, int socCode)  {
        checkNameRules(name);
        checkRoleRules(role);
        this.company = App.getInstance().getCompany();
        this.name = name;
        this.role = company.getOrgRoleByName(role);
        this.address = address;
        this.email = email;
        this.empID = generateID(name);
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
        company.numEmp++;
    }
    private void checkNameRules (String name) {

        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( name.length() > 35 )
            throw new IllegalArgumentException("Name must not have more than 35 chars.");
    }

    private void checkRoleRules(String role) {

        if (StringUtils.isBlank(role))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( role.length() > 15 )
            throw new IllegalArgumentException("Name must not have more than 15 chars.");
    }
    public String generateID(String name) {
        String id = "";
        int x = 0;
        char[] charsArray = new char[10];
        if (!(name.charAt(0) == ' ')) {
            charsArray[0] = name.charAt(0);
        }
        for (int i = 1; i < name.length() - 1; i++)
            if (name.charAt(i) == ' '){
                x++;
                charsArray[x] = name.charAt(i+1);
            }
        for (char c : charsArray)
            id = id + c;
        String y = String.valueOf(company.numEmp);
        int z = y.length();
        String esp = "0";
        for (int i = 0; i < (5-z); i++)
            id = id + esp;
        id = id + company.numEmp;
        return id;
    }

}
