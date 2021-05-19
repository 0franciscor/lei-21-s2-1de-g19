package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the receptionist UI.
 *
 * @author Eduardo Gonçalves
 */
public class ReceptionistUI implements Runnable {

    /**
     * Empty constructor.
     */
    public ReceptionistUI()
    {
    }

    /**
     * Allows you to select from a menu of options, the desired option.
     */
    public void run()
    {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a new client", new RegisterClientUI()));
        options.add(new MenuItem("Register a test to be performed to a registered client", new RegisterTestUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
