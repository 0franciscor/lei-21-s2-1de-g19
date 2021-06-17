package app.ui;


import app.domain.model.Client;
import app.domain.model.SendNHSReportTimerTask;
import app.domain.model.SortAlgorithm2;
import app.ui.console.MainMenuUI;
import auth.mappers.dto.ClientDto;


import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
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
            SendNHSReportTimerTask task1 = new SendNHSReportTimerTask();
            Calendar data = Calendar.getInstance();
            data.set(Calendar.HOUR_OF_DAY, 6);
            data.set(Calendar.MINUTE, 0);
            data.set(Calendar.SECOND,0);
            Timer timer = new Timer();
            timer.schedule(task1, data.getTime(), 1000*60*60*24);

            MainMenuUI menu = new MainMenuUI();
            menu.run();
            
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
