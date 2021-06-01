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

    /**
     * Allows access to update client data controller methods.
     */
    public UpdateClientDataUI (){

        ctrl = new UpdateClientDataController();
    }


    public void run() {

        System.out.printf("\n--- UPDATE CLIENT'S DATA ---");
        String TIN = Utils.readLineFromConsole("\nType the Client's Tax Identification Number:");

        Client cl;

        try{
            cl = ctrl.getClient(TIN);
        }catch (Exception e){
            System.out.println("\nInvalid Tax Identification Number or the client has not been registered yet.");
            return;
        }

        if (cl != null){

            boolean confirmation = Utils.confirm(String.format("Is this information right? If so type s, if not type n. \n\n Citizen card number: %s \n National Healthcare Service number (NHS): %s " +
                    "\n Birth date: %s \n Sex: %s \n Tax Identification number (TIN): %s \n Phone number: %s \n email: %s \n name: %s ", cl.getCitizenID(), cl.getNhsID(), cl.getBirthDate(), cl.getSex(), cl.getTIN(), cl.getPhoneNumber(), cl.getEmail(), cl.getName()));

            if (confirmation){

                List<String> dataToUpdate = ctrl.getDataToUpdate(cl);

                System.out.println("\n--- DATA YOU CAN UPDATE ---");

                List<String> options = new ArrayList<>();
                options.add("Sex");
                options.add("Phone number");
                options.add("Email");
                options.add("Name");

                int option = Utils.showAndSelectIndex(options,"Select an option from the list.");

                mudarDados(option,cl);

            }
        }
    }

    public void mudarDados (int option, Client client){

        switch (option){
            case 0:
                System.out.println("Old sex:" + client.getSex());
                boolean confirmation = Utils.confirm("Change sex?");
                if (confirmation){
                    boolean aux = false;
                    do{
                        try {
                            client.setSex(Utils.readLineFromConsole("Updated sex:"));
                        } catch (Exception e) {
                            System.out.println("Invalid sex.");
                            aux = true;
                        }
                    }while(!aux);

                }
                break;
            case 1:
                System.out.println("Old phone number:" + client.getPhoneNumber());
                boolean confirmation1 = Utils.confirm("Change phone number?");
                if (confirmation1){
                    client.setSex(Utils.readLineFromConsole("Updated phone number:"));
                }
                break;
            case 2:
                System.out.println("Old email:" + client.getEmail());
                boolean confirmation2 = Utils.confirm("Change email?");
                if (confirmation2){
                    client.setSex(Utils.readLineFromConsole("Updated email:"));
                }
                break;
            case 3:
                System.out.println("Old name:" + client.getName());
                boolean confirmation3 = Utils.confirm("Change name?");
                if (confirmation3){
                    client.setSex(Utils.readLineFromConsole("Updated name:"));
                }
                break;
        }
    }
}
