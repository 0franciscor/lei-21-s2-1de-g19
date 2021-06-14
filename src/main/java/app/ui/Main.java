package app.ui;

import app.controller.App;
import app.domain.model.Notification;
import app.domain.model.SendNHSReportTimerTask;
import app.ui.console.MainMenuUI;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Main {

    public static void main(String[] args)
    {
        try
        {
            /*SendNHSReportTimerTask task1 = new SendNHSReportTimerTask();
            Timer timer = new Timer();
            timer.schedule(task1, new Time(6,0,0));*/
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
