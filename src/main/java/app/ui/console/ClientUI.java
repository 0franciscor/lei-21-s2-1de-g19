package app.ui.console;

import app.ui.console.utils.Utils;

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
        options.add(new MenuItem("View Tests Results", new ViewClientTestsUI()));
        options.add(new MenuItem("Update personal data", new RegisterClientUI()));

        int option = 0;
        do
        {
            boolean exceptionThrown = false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");
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