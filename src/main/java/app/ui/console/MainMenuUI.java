package app.ui.console;

import app.controller.App;
import app.domain.model.Company;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class MainMenuUI {

    public MainMenuUI()
    {
    }

    public void run() throws IOException
    {
        Company company = App.getInstance().getCompany();


        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthUI()));
        options.add(new MenuItem("Know the Development Team",new DevTeamUI()));
        int option = 0;

        do
        {
            boolean exceptionThrown = false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nMain Menu");
            } catch (Exception e) {
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }

            if ((option >= 0) && (option < options.size()) && !exceptionThrown)
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
