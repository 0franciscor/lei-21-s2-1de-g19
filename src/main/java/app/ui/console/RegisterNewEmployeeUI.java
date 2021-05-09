package app.ui.console;


import app.controller.RegisterNewEmployeeController;
import app.domain.model.Employee;
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
            String name = Utils.readLineFromConsole("Type the employee name:");
            String address = Utils.readLineFromConsole("Type the employee address:");
            String email = Utils.readLineFromConsole("Type the employee email:");
            String phoneNumber = Utils.readLineFromConsole("Type the employee phone number:");
            int socCode = Utils.readIntegerFromConsole("Type the employee Standard Occupational Classification code:");
            List<OrgRoleDto> list = ctrl.getRoles();
            System.out.println("This is the list of roles. ");
            for (OrgRoleDto c : list)
                System.out.println(c.getDesignation());
            String role = Utils.readLineFromConsole("Type the one you desire to register an employee.");
            if (role.equalsIgnoreCase("Specialist Doctor")){
                role = "Specialist Doctor";
                int doctorIndexNumber = Utils.readIntegerFromConsole("Type the employee Doctor Index Number:");
                EmployeeDto empDto = new EmployeeDto(name, role, address, email, phoneNumber, socCode, doctorIndexNumber);
                Employee emp = ctrl.createEmployee(empDto);
                confirmation = Utils.readIntegerFromConsole(String.format("Are you sure this is the info of the client ? If so type 1, if not type 2. \n\n Name: %s \n Role: %s " +
                        "\n Address: %s \n Email: %s \n Phone Number: %d \n Standard Occupational Classification code: %d \n Doctor Index Number: %d ", name,role, address, email, phoneNumber, socCode, doctorIndexNumber));
                if (confirmation == 1) {
                    if (ctrl.saveEmployee(emp));
                    System.out.println("Employee registered with success");
                } else {
                    empDto = new EmployeeDto(name, role, address, email, phoneNumber, socCode);
                    emp = ctrl.createEmployee(empDto);
                    confirmation = Utils.readIntegerFromConsole(String.format("Are you sure this is the info of the client ? If so type 1, if not type 2. \n\n Name: %s \n Role: %s " +
                            "\n Address: %s \n Email: %s \n Phone Number: %d \n Standard Occupational Classification code: %d ", name,role, address, email, phoneNumber, socCode));
                    if (confirmation == 1)
                        if (ctrl.saveEmployee(emp));
                    System.out.println("Employee registered with success");
                }
        }


    } while (confirmation == 0);

}
}

