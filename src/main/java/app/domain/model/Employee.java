package app.domain.model;

import app.controller.App;
import auth.mappers.dto.EmployeeDto;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
/**
 * Represents an Employee.
 *
 * @author Alexandre Soares
 */
public class Employee {
    /**
     * The employee's Company.
     */
    public Company company = App.getInstance().getCompany();
    /**
     * The employee's name.
     */
    public String name;
    /**
     * The employee's Organization Role.
     */
    public OrgRole role;
    /**
     * The employee's address.
     */
    private String address;
    /**
     * The employee's email.
     */
    public String email;
    /**
     * The employee's ID.
     */
    private String empID;
    /**
     * The employee's phone number.
     */
    private String phoneNumber;
    /**
     * The employee's Standard Occupational Classification code.
     */
    private int socCode;
    /**
     * The employee's Doctor Index Number.
     */
    private int doctorIndexNumb;
    /**
     * Builds a new Employee object without defining any attributes
     *
     */
    public Employee () {
    }
    /**
     * Builds an Employee object receiving a name, role, address, email, phone number, Standard Occupational Classification code
     * and Doctor Index Number.
     * @param name Employee's name.
     * @param role Employee's role.
     * @param address Employee's address.
     * @param email Employee's email.
     * @param phoneNumber Employee's phone number.
     * @param socCode Employee's Standard Occupational Classification code.
     * @param doctorIndexNumb Employee's Doctor Index Number.
     *
     * @return An Employee object.
     */
    public Employee (String name, OrgRole role, String address, String email,String phoneNumber, int socCode, int doctorIndexNumb)  {
        checkNameRules(name);
        checkRoleRules(role.designation);
        checkDoctorIndexNumberRules(doctorIndexNumb);
        checkSOCCODERules(socCode);
        this.name = name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.empID = generateID(name);
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
        this.doctorIndexNumb = doctorIndexNumb;
        company.numEmp++;
    }
    /**
     * Builds an Employee object receiving a name, role, address, email, phone number and Standard Occupational Classification code.
     *
     * @param name Employee's name.
     * @param role Employee's role.
     * @param address Employee's address.
     * @param email Employee's email.
     * @param phoneNumber Employee's phone number.
     * @param socCode Employee's Standard Occupational Classification code.
     *
     * @return An Employee object.
     */
    public Employee (String name, OrgRole role, String address, String email, String phoneNumber, int socCode)  {
        checkNameRules(name);
        checkRoleRules(role.designation);
        checkPhoneNumberRules(phoneNumber);
        checkSOCCODERules(socCode);
        this.name = name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.empID = generateID(name);
        this.phoneNumber = phoneNumber;
        this.socCode = socCode;
        company.numEmp++;
    }
    /**
     * Method responsible for checking the acceptance criteria for the Employee's name.
     *
     * @param name Employee's name.
     *
     *
     */
    private void checkNameRules (String name) {

        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( name.length() > 35 )
            throw new IllegalArgumentException("Name must not have more than 35 chars.");
    }
    /**
     * Method responsible for checking the acceptance criteria for the Employee's role.
     *
     * @param role Employee's role.
     *
     *
     */
    private void checkRoleRules(String role) {

        if (StringUtils.isBlank(role))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( role.length() > 15 )
            throw new IllegalArgumentException("Role must not have more than 15 chars.");
        if (company.getOrgRoleByName(role).designation == null)
            throw new IllegalArgumentException("This role does not exist.");
    }
    /**
     * Method responsible for checking the acceptance criteria for the Employee's role.
     *
     * @param phoneNumber Employee's phone number.
     *
     *
     */
    private void checkPhoneNumberRules (String phoneNumber){

        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone number cannot be blank.");
        if ( phoneNumber.length() != 11 ){
            throw new IllegalArgumentException("Phone number must have 11 chars.");
        }
        for (int i = 0; i<phoneNumber.length(); i++){
            char y = phoneNumber.charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 ){
                throw new IllegalArgumentException("Phone number must be only numbers.");
            }
        }
    }
    /**
     * Method responsible for checking the acceptance criteria for the Employee's Standard Occupational Classification code.
     *
     * @param socCode Employee's Standard Occupational Classification code.
     *
     *
     */
    private void checkSOCCODERules (int socCode){

        if (StringUtils.isBlank(String.valueOf(socCode)))
            throw new IllegalArgumentException("Phone number cannot be blank.");
        if ( String.valueOf(socCode).length() != 4 ){
            throw new IllegalArgumentException("Phone number must have 11 chars.");
        }
        for (int i = 0; i<String.valueOf(socCode).length(); i++){
            char y = String.valueOf(socCode).charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 ){
                throw new IllegalArgumentException("Standard Occupational Classification code must be only numbers.");
            }
        }
    }
    /**
     * Method responsible for checking the acceptance criteria for the Employee's Doctor Index Number.
     *
     * @param doctorIndexNumb Employee's Doctor Index Number.
     *
     *
     */
    private void checkDoctorIndexNumberRules (int doctorIndexNumb){

        if (StringUtils.isBlank(String.valueOf(doctorIndexNumb)))
            throw new IllegalArgumentException("Phone number cannot be blank.");
        if ( String.valueOf(doctorIndexNumb).length() != 6 ){
            throw new IllegalArgumentException("Phone number must have 11 chars.");
        }
        for (int i = 0; i<String.valueOf(doctorIndexNumb).length(); i++){
            char y = String.valueOf(doctorIndexNumb).charAt(i);
            if (y != 48 && y != 49 && y != 50 && y != 51 && y != 52 && y != 53 && y != 54 && y != 55 && y != 56 && y != 57 ){
                throw new IllegalArgumentException("Standard Occupational Classification code must be only numbers.");
            }
        }
    }
    /**
     * Method responsible for generating the Employee ID.
     *
     * @param name Employee's name.
     *
     * @return String Employee's ID.
     */
    private String generateID(String name) {
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
    /**
     * Method responsible for generating the Employee's password to use tha application.
     *
     * @return String Employee's password.
     */
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

}
