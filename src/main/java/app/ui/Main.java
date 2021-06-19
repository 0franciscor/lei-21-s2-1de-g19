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
            //menu.run();

            List<Date> dateList = new ArrayList<>();

            double[] x = {35.3,29.7,30.8,58.8,61.4,71.3,74.4,76.7,70.7,57.5,46.4,28.9,28.1,39.1,46.8,48.5,59.3,70,70,74.5,72.1,58.1,44.6,33.4,28.6};

            double[] y = {10.98,11.13,12.51,8.40,9.27,8.73,6.36,8.50,7.82,9.14,8.24,12.19,11.88,9.57,10.94,9.58,10.09,8.11,6.83,8.88,7.68,8.47,8.86,10.36,11.08};

            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());
            dateList.add(new Date());

            System.out.println(new NHSReport(95, 95, false).calculateData(x,y, dateList));

        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
