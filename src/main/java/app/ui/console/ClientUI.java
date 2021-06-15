package app.ui.console;

import app.controller.App;
import app.domain.model.Client;
import app.ui.console.utils.Utils;
import auth.AuthFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Client UI.
 *
 * @author Eduardo Gonçalves, Rita Lello
 */
public class ClientUI implements Runnable{


    /**
     * Empty constructor.
     */
    public ClientUI() {
    }

    /**
     * Allows you to select from a menu of options, the desired option.
     */
    public void run()
    {
        List<MenuItem> options = new ArrayList<>();

        String TIN = null;

        String email = new AuthFacade().getCurrentUserSession().getUserId().getEmail();

        List<Client> clientList = App.getInstance().getCompany().getClientStore().getClientList();

        for(Client client : clientList){
            if(client.getEmail().equalsIgnoreCase(email)){
                TIN = client.getTIN();
            }
        }

        options.add(new MenuItem("View Tests Results", new ViewClientTestsUI(TIN)));
        options.add(new MenuItem("Update personal data", new UpdateClientDataUI(TIN)));

        int option = 0;
        do
        {
            boolean exceptionThrown = false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");
            } catch (Exception e) {
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }

            if ( (option >= 0) && (option < options.size()) && !exceptionThrown)
            {
                options.get(option).run();
            }
        } while (option != -1 );
    }
}
