package auth.mappers;


import app.domain.model.Company;
import app.domain.model.Employee;

import app.domain.model.OrgRole;
import app.domain.model.SpecialistDoctor;
import auth.mappers.dto.EmployeeDto;

import java.util.List;
/**
 * Class responsible for converting an Employee Data Transfer object to an Employee object.
 *
 * @author Alexandre Soares
 */
public class EmployeeMapper {
    /**
     * Method responsible to return this Employee object.
     *
     * @param employeeDto an Employee Data Transfer object.
     * @return EmpStore
     */
    public static Employee toModel (EmployeeDto  employeeDto){

        String name = employeeDto.getName();
        String address = employeeDto.getAddress();
        String email = employeeDto.getEmail();
        String role = employeeDto.getRole();
        String phoneNumber = employeeDto.getPhoneNumber();
        int socCode = employeeDto.getSocCode();
        int doctorIndexNumb = employeeDto.getDoctorIndexNumb();

        if (role.equalsIgnoreCase("SpecDoctor")) {
            SpecialistDoctor c = new SpecialistDoctor(name, role, address, email, phoneNumber, socCode, doctorIndexNumb);
        }

        Employee c = new Employee(name, role, address, email, phoneNumber, socCode);


        return c;
    }
}
