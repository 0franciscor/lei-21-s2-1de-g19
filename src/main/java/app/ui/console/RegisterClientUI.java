package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Client;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ClientDto;

/**
 * Represents the register client UI.
 *
 * @author Eduardo Gonçalves
 */
public class RegisterClientUI implements Runnable {

    /**
     * Register Client Controller.
     */
    private RegisterClientController ctrl;

    /**
     * Allows access to register client controller methods.
     */
    public RegisterClientUI() {

        ctrl = new RegisterClientController();
    }

    /**
     * It allows you to enter the data necessary to register a client, make the confirmation and see if the operation was successful or not.
     */
    public void run(){

        System.out.printf("\n--- Requested data to register a client ---");
        String citizenID = Utils.readLineFromConsole("\nType the client's citizen card numer:");
        String nhsID = Utils.readLineFromConsole("Type the client's National Healthcare Service number (NHS):");
        String birthDate = Utils.readLineFromConsole("Type the client's birth date:");
        String sex = Utils.readLineFromConsole("Type the client's sex: ");
        String TIN = Utils.readLineFromConsole("Type the client's Tax Identification number (TIN): ");
        String phoneNumber = Utils.readLineFromConsole("Type the client's phone number: ");
        String email = Utils.readLineFromConsole("Type the client's email: ");
        String name = Utils.readLineFromConsole("Type the client's name: ");
        String address = Utils.readLineFromConsole("Type the client's address: ");

        boolean confirmation = Utils.confirm(String.format("Are you sure this is the info of the client ? If so type s, if not type n. \n\n Citizen card number: %s \n National Healthcare Service number (NHS): %s " +
                "\n Birth date: %s \n Sex: %s \n Tax Identification number (TIN): %s \n Phone number: %s \n email: %s \n name: %s \n address: %s", citizenID,nhsID, birthDate, sex, TIN, phoneNumber, email, name, address));

        if (confirmation) {
            ClientDto dto = new ClientDto(citizenID, nhsID, birthDate, sex, TIN, phoneNumber, email, name, address);

            Client cl;
            try {
                cl = ctrl.registerClient(dto);
            } catch (Exception e) {
                System.out.println("Error registering the client. Check the data entered and re-register.");
                return;
            }

            try {
                ctrl.saveClient(cl);
            } catch (Exception e){
                System.out.println("There was an error when saving the client.");
            }

            System.out.println("Operation was a success.");

        }
    }
}
