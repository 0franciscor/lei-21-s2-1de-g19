/*
package app.domain.model;

import java.io.*;
import java.util.Properties;
import java.util.TimerTask;

public class SendNHSReportTimerTask extends TimerTask {

    public  SendNHSReportTimerTask(){

    }
    @Override
    public void run() {
        File configFile = new File("config.properties");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String key = properties.getProperty("algoritmo");
        if (key == "MultiLinear"){
            NHSReport nhsReport = new NHSReport();
            nhsReport.calculateDataMultiLinear();
        }
        if (key == "Linear"){
            NHSReport nhsReport = new NHSReport();
            nhsReport.calculateDataMultiLinear();
        }
    }
}
*/
