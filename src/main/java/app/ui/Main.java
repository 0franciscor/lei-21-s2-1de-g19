package app.ui;

import app.controller.App;
import app.domain.model.Notification;
import app.ui.console.MainMenuUI;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Main {

    public static void main(String[] args)
    {
        try
        {
            MainMenuUI menu = new MainMenuUI();

            menu.run();
            Notification notification = App.getInstance().getCompany().getNotificationService();
            notification.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
