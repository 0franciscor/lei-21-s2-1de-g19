package app.ui.console;


import app.controller.RegisterNewEmployeeController;
import app.domain.model.Employee;
import app.domain.model.OrgRole;
import app.ui.console.utils.Utils;
import auth.mappers.dto.EmployeeDto;
import auth.mappers.dto.OrgRoleDto;

import java.util.List;

public class RegisterNewEmployeeUI implements Runnable {
    private RegisterNewEmployeeController ctrl;
    public RegisterNewEmployeeUI() { ctrl = new RegisterNewEmployeeController(); }
    /**
     * It allows you to enter the data necessary to register an Employee, make the confirmation and see if the operation was successful or not.
     */
    public void run(){
        int confirmation = 0;
        do {
            System.out.println("------ Requested data for registering an Employee. ------");
            String name = Utils.readLineFromConsole("Type the employee name:");
            String address = Utils.readLineFromConsole("Type the employee address:");
            String email = Utils.readLineFromConsole("Type the employee email:");
            String phoneNumber = Utils.readLineFromConsole("Type the employee phone number:");
            int socCode = Utils.readIntegerFromConsole("Type the employee Standard Occupational Classification code:");
            List<OrgRoleDto> list = ctrl.getRoles();
            System.out.println("This is the list of roles. ");
            for (OrgRoleDto c : list)
                System.out.println(c.getDesignation());
            String roleName = Utils.readLineFromConsole("Type the employee role.");
            if (ctrl.company.getOrgRoleByName(roleName).designation == null)
                throw new IllegalArgumentException("This role does not exist.");
            OrgRole role = ctrl.company.getOrgRoleByName(roleName);
            if (roleName.equalsIgnoreCase("SPEC DOCTOR")){
                roleName = "SPEC DOCTOR";
                int doctorIndexNumber = Utils.readIntegerFromConsole("Type the employee Doctor Index Number:");
                confirmation = Utils.readIntegerFromConsole(String.format("Are you sure this is the info of the client ? If so type 1, if not type 2. \n\n Name: %s \n Role: %s " +
                        "\n Address: %s \n Email: %s \n Phone Number: %s \n Standard Occupational Classification code: %d \n Doctor Index Number: %d ", name,roleName, address, email, phoneNumber, socCode, doctorIndexNumber));
                if (confirmation == 1) {
                    EmployeeDto empDto = new EmployeeDto(name, role, address, email, phoneNumber, socCode, doctorIndexNumber);
                    Employee emp = ctrl.createEmployee(empDto);
                    if (ctrl.saveEmployee(emp)) {
                        System.out.println("Employee registered with success");
                        System.out.println("This is the list of Employees registered on the database.");
                        for (Employee c : ctrl.empStore.getEmpList()) {
                            System.out.println(c.toString());
                        }
                    } else {
                        System.out.println("Employee is already registered");
                    }
                } else {
                    run();
                }

        } else {
                confirmation = Utils.readIntegerFromConsole(String.format("Are you sure this is the info of the client ? If so type 1, if not type 2. \n\n Name: %s \n Role: %s " +
                        "\n Address: %s \n Email: %s \n Phone Number: %s \n Standard Occupational Classification code: %d ", name,roleName, address, email, phoneNumber, socCode));
                if (confirmation == 1) {
                    EmployeeDto empDto = new EmployeeDto(name, role, address, email, phoneNumber, socCode);
                    Employee emp = ctrl.createEmployee(empDto);
                    if (ctrl.saveEmployee(emp)) {
                        System.out.println("Employee registered with success");
                        System.out.println("This is the list of Employees registered on the database.");
                        for (Employee c : ctrl.empStore.empList) {
                            System.out.println(c.toString());
                        }
                    } else {
                        System.out.println("Employee is already registered");
                    }
                } else {
                    run();
                }
            }



    } while (confirmation == 0);

}
}

