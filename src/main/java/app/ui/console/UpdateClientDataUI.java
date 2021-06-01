package app.ui.console;

import app.controller.UpdateClientDataController;
import app.domain.model.Client;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents update client data UI.
 *
 * @author Eduardo Gonçalves, Rita Lello
 */
public class UpdateClientDataUI implements Runnable {

    /**
     * Update client data Controller.
     */
    private UpdateClientDataController ctrl;
    private String TIN;

    /**
     * Allows access to update client data controller methods.
     */
    public UpdateClientDataUI (String TIN){

        ctrl = new UpdateClientDataController();
        this.TIN = TIN;
    }


    public void run() {

        /*
        System.out.printf("\n--- UPDATE CLIENT'S DATA ---");
        String TIN = Utils.readLineFromConsole("\nType the Client's Tax Identification Number:");

        Client cl;

        try{
            cl = ctrl.getClient(TIN);
        }catch (Exception e){
            System.out.println("\nInvalid Tax Identification Number or the client has not been registered yet.");
            return;
        }

         */

        Client cl = ctrl.getClient(TIN);

        if (cl != null){

            boolean confirmation = Utils.confirm(String.format("Is this information right? If so type s, if not type n. \n\n Citizen card number: %s \n National Healthcare Service number (NHS): %s " +
                    "\n Birth date: %s \n Sex: %s \n Tax Identification number (TIN): %s \n Phone number: %s \n email: %s \n name: %s ", cl.getCitizenID(), cl.getNhsID(), cl.getBirthDate(), cl.getSex(), cl.getTIN(), cl.getPhoneNumber(), cl.getEmail(), cl.getName()));

            if (confirmation){

                System.out.println("\n--- DATA YOU CAN UPDATE ---");

                List<String> options = new ArrayList<>();
                options.add("Sex");
                options.add("Phone number");
                options.add("Email");
                options.add("Name");

                int option = Utils.showAndSelectIndex(options,"\nSelect an option from the list.");

                mudarDados(option,cl);

            }
        }
    }

    public void mudarDados (int option, Client client){

        switch (option){
            case 0:
                System.out.println("\nOld sex is: " + client.getSex());
                boolean confirmation = Utils.confirm("Do you want to change your sex?");
                if (confirmation){
                    boolean aux = false;
                    do {
                        try {
                            client.setSex(Utils.readLineFromConsole("Updated sex:"));
                            aux = true;
                        } catch (Exception e) {
                            System.out.println("\nInvalid sex. Type a valid one.");

                        }
                    } while (!aux);
                }
                break;
            case 1:
                System.out.println("\nOld phone number: " + client.getPhoneNumber());
                boolean confirmation1 = Utils.confirm("Do you want to change your phone number?");
                if (confirmation1){
                    boolean aux1 = false;
                    do {
                        try {
                            client.setPhoneNumber(Utils.readLineFromConsole("Updated phone number:"));
                            aux1 = true;
                        } catch (Exception e) {
                            System.out.println("\nInvalid phone number. Type a valid one.");
                        }
                    } while (!aux1);
                }
                break;
            case 2:
                System.out.println("\nOld email: " + client.getEmail());
                boolean confirmation2 = Utils.confirm("Do you want to change your email?");
                if (confirmation2){
                    client.setEmail(Utils.readLineFromConsole("Updated email:"));
                }
                break;
            case 3:
                System.out.println("\nOld name: " + client.getName());
                boolean confirmation3 = Utils.confirm("Do you want to change your name?");
                if (confirmation3){
                    boolean aux3 = false;
                    do {
                        try {
                            client.setName(Utils.readLineFromConsole("Updated name:"));
                            aux3 = true;
                        } catch (Exception e) {
                            System.out.println("\nInvalid name. Type a valid one.");
                        }
                    } while (!aux3);
                }
                break;
        }
    }
}
