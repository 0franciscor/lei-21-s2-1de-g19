package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Client;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ClientDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        ClientDto dto = new ClientDto(citizenID, nhsID, birthDate, sex, TIN, phoneNumber, email, name);

        Client cl;
        try {
            cl = ctrl.registerClient(dto);
        } catch (Exception e) {
            System.out.println("Erro ao registar o cliente. Verifique os dados introduzidos.");
            return;
        }

        boolean confirmation = Utils.confirm(String.format("Are you sure this is the info of the client ? If so type s, if not type n. \n\n Citizen card number: %s \n National Healthcare Service number (NHS): %s " +
                "\n Birth date: %s \n Sex: %s \n Tax Identification number (TIN): %s \n Phone number: %s \n email: %s \n name: %s ", citizenID,nhsID, birthDate, sex, TIN, phoneNumber, email, name));

        if(confirmation){
            ctrl.saveClient(cl);
            System.out.println("Operation was a success.");
        }

        /*
        for (Client x: ctrl.clientstore.getList()){
            System.out.println(x.getEmail());
            System.out.println(x.getBirthDate());
            System.out.println(x.getSex());
            System.out.println(x.getName());
            System.out.println(x.getCitizenID());
            System.out.println(x.getNhsID());
            System.out.println(x.getPhoneNumber());
            System.out.println(x.getTIN());
        }

         */


    }
}
