package app.ui;


import app.domain.model.Client;
import app.domain.model.NHSReport;
import app.domain.model.SendNHSReportTimerTask;
import app.domain.model.SortAlgorithm2;
import app.ui.console.MainMenuUI;
import auth.mappers.dto.ClientDto;


import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
            Timer scheduler = new Timer();
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int miliseconds = calendar.get(Calendar.MILLISECOND) + calendar.get(Calendar.SECOND)*1000 + calendar.get(Calendar.MINUTE)*60*1000 + calendar.get(Calendar.HOUR_OF_DAY)*60*60*1000;
            int delayIMilliseconds = miliseconds < 1000*60*60*6 ? 1000*60*60*6 - miliseconds : 1000*60*60*24 - (miliseconds - 1000*60*60*6);
            scheduler.scheduleAtFixedRate(task1, delayIMilliseconds, 1000*60*60*24);

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
