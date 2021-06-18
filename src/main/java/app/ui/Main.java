package app.ui;


import app.domain.model.Client;
import app.domain.model.NHSReport;
import app.domain.model.SendNHSReportTimerTask;
import app.domain.model.SortAlgorithm2;
import app.ui.console.MainMenuUI;
import auth.mappers.dto.ClientDto;


import java.util.*;

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

            /*
            List<Date> dateList = new ArrayList<>();

            double[] x = {65,71,69,75,78,66,74};

            double[] y = {63,69,64,76,75,65,70};

            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());

            System.out.println(new NHSReport(0.95).calculateData(x,y, dateList));

             */

        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
