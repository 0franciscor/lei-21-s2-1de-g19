package app.domain.model;

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

    public Employee createEmployee(EmployeeDto empDto) {
        if (empDto.getRole().equalsIgnoreCase("Specialist Doctor")) {
            OrgRole orgRole = company.getOrgRoleByName("Specialist Doctor");
            if (orgRole.designation == null) {
                return new Employee();
            }
            Employee emp = new Employee(empDto.getName(), orgRole, empDto.getAddress(), empDto.getEmail(), empDto.getPhoneNumber(), empDto.getSocCode(), empDto.getDoctorIndexNumb());
            return emp;
        }
        OrgRole orgRole1 = company.getOrgRoleByName(empDto.getRole());
        Employee emp1 = new Employee(empDto.getName(), orgRole1, empDto.getAddress(), empDto.getEmail(), empDto.getPhoneNumber(), empDto.getSocCode());
        return emp1;
    }


    public Employee () {

    }
    public Employee (String name, OrgRole role, String address, String email,int phoneNumber, int socCode, int doctorIndexNumb)  {
        checkNameRules(name);
        checkRoleRules(role);
        this.name = name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.empID = generateID();
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
        this.doctorIndexNumb = doctorIndexNumb;
        company.numEmp++;
    }
    public Employee (String name, OrgRole role, String address, String email, int phoneNumber, int socCode)  {
        this.name = name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.empID = empID;
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

    private void checkRoleRules(OrgRole role) {

        if (StringUtils.isBlank(role.designation))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( role.designation.length() > 15 )
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
                charsArray[x] = name.charAt(i+1);
                x++;
            }

        for (char c : charsArray)
            id = id + c;
        id = id + company.numbEmp;
        return id;
    }

}
