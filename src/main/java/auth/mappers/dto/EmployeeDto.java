package auth.mappers.dto;

import app.domain.model.OrgRole;
/**
 * Represents an Employee Data Transfer object.
 *
 * @author Alexandre Soares
 */
public class EmployeeDto {
    /**
     * The Employee Data Transfer object name.
     */
    public String name;
    /**
     * The Employee Data Transfer object Organization Role.
     */
    public OrgRole role;
    /**
     * The Employee Data Transfer object address.
     */
    private String address;
    /**
     * The Employee Data Transfer object email.
     */
    public String email;
    /**
     * The Employee Data Transfer object phone number.
     */
    private String phoneNumber;
    /**
     * The Employee Data Transfer object Standard Occupational Classification code.
     */
    private int socCode;
    /**
     * The Employee Data Transfer object Doctor Index Number.
     */
    private int doctorIndexNumb;
    /**
     * Builds an Employee Data Transfer object receiving a name, role, address, email, phone number and Standard Occupational Classification code.
     *
     * @param name Employee Data Transfer object's name.
     * @param role Employee Data Transfer object's role.
     * @param address Employee Data Transfer object's address.
     * @param email Employee Data Transfer object's email.
     * @param phoneNumber Employee Data Transfer object's phone number.
     * @param socCode Employee Data Transfer object's Standard Occupational Classification code.
     *
     * @return An Employee Data Transfer object.
     */
    public EmployeeDto (String name, OrgRole role, String address, String email, String phoneNumber, int socCode) {
        this.name= name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
    }
    /**
     * Builds an Employee Data Transfer object receiving a name, role, address, email, phone number, Standard Occupational Classification code
     * and Doctor Index Number.
     * @param name Employee Data Transfer object's name.
     * @param role Employee Data Transfer object's role.
     * @param address Employee Data Transfer object's address.
     * @param email Employee Data Transfer object's email.
     * @param phoneNumber Employee Data Transfer object's phone number.
     * @param socCode Employee Data Transfer object's Standard Occupational Classification code.
     * @param doctorIndexNumb Employee Data Transfer object's Doctor Index Number.
     *
     * @return An Employee Data Transfer object.
     */
    public EmployeeDto (String name, OrgRole role, String address, String email, String phoneNumber, int socCode, int doctorIndexNumb) {
        this.name= name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
        this.doctorIndexNumb = doctorIndexNumb;
    }
    /**
     * Method responsible for return the Employee Data Transfer object's Doctor Index Number.
     *
     * @return int Doctor Index Number.
     */
    public int getDoctorIndexNumb() {
        return doctorIndexNumb;
    }
    /**
     * Method responsible for return the Employee Data Transfer object's Phone Number.
     *
     * @return int Phone Number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Method responsible for return the Employee Data Transfer object's role.
     *
     * @return String role.
     */
    public OrgRole getRole() {
        return role;
    }
    /**
     * Method responsible for return the Employee Data Transfer object's name.
     *
     * @return String name.
     */
    public String getName() {
        return name;
    }
    /**
     * Method responsible for return the Employee Data Transfer object's Standard Occupational Classification code.
     *
     * @return int socCode.
     */
    public int getSocCode() {
        return socCode;
    }
    /**
     * Method responsible for return the Employee Data Transfer object's address.
     *
     * @return String address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Method responsible for return the Employee Data Transfer object's email.
     *
     * @return String email.
     */
    public String getEmail() {
        return email;
    }

}
