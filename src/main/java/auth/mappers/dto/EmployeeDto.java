package auth.mappers.dto;

import app.domain.model.Employee;
import app.domain.model.OrgRole;

public class EmployeeDto {
    private String name;
    private String address;
    private String email;
    private String role;
    private int phoneNumber;
    private int socCode;
    private int doctorIndexNumb;

    public EmployeeDto (String name, String role, String address, String email, int phoneNumber, int socCode) {
        this.name= name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
    }
    public EmployeeDto (String name, String role, String address, String email, int phoneNumber, int socCode, int doctorIndexNumb) {
        this.name= name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
        this.doctorIndexNumb = doctorIndexNumb;
    }
    public int getDoctorIndexNumb() {
        return doctorIndexNumb;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public int getSocCode() {
        return socCode;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setRoleId(String role) {
        this.role = role;
    }
}
